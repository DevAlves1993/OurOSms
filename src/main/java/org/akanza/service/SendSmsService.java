package org.akanza.service;

import io.github.devalves.osms.model.response.ResponseSMS;
import io.github.devalves.osms.model.response.error.ResponseError;
import org.akanza.model.*;
import io.github.devalves.osms.OSms;
import io.github.devalves.osms.core.CountryCode;
import io.github.devalves.osms.core.exception.HttpApiOrangeException;
import io.github.devalves.osms.model.OrangeSMS;
import io.github.devalves.osms.model.response.error.ServiceException;
import org.akanza.service.exception.ServiceIoConnectivityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@Service
public class SendSmsService
{
    private final Logger LOG = LoggerFactory.getLogger(SendSmsService.class);
    @Autowired
    private SmsService smsService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private UserService userService;
    @Autowired
    private SmsSentService smsSentService;


    public SMS saveSMS(String senderAddress,Optional<String> senderName,String receiver,String content
            ,String country)
    {
        SMS sms = senderName.map(s -> new SMS(receiver, content, s, senderAddress, country)).orElseGet(() -> new SMS(receiver, content, "", senderAddress, country));
        return smsService.save(sms);
    }

    public SmsSent.SentErrorStatus sent(SMS sms)
    {
        String senderAddress = sms.getSenderAddress();
        String senderName = sms.getSenderName();
        String receiver = sms.getReceiver();
        String country = sms.getCountry();
        String content = sms.getContent();
        Optional<Object> optional = sendSms(senderAddress,senderName,receiver,content,country);
        if(optional.isPresent())
        {
            long id = sms.getId();
            Object result = optional.get();
            if(result instanceof ResponseSMS)
            {
                LOG.info("Sms send with successfully");
                ResponseSMS.SMSResponse SMSResponse = ((ResponseSMS) result).getOutBoundSMSMessageRequest();
                LOG.info("Address SMS : "+SMSResponse.getAddress()+" [ID SMS : "+id+"]");
                LOG.info("Sender Address SMS : "+SMSResponse.getSenderAddress()+" [ID SMS : "+id+"]");
                return SmsSent.SentErrorStatus.NOTHING;
            }
            else if(result instanceof ServiceException)
            {
                LOG.info("Sending sms to fail");
                LOG.info("The error is caused by Orange Service");
                String messageId = ((ServiceException) result).getMessageId();
                LOG.error("Message Id Error : "+messageId+" [ID SMS : "+id+"]");
                String messageText = ((ServiceException) result).getText();
                LOG.error("Message Text : "+messageText+" [ID SMS : "+id+"]");
                List<String> variables = ((ServiceException) result).getVariables();
                variables.forEach((s) -> LOG.error("Variable Error : "+s+" [ID SMS : "+id+"]"));
                return SmsSent.SentErrorStatus.SERVICE_ERROR;
            }
            else if(result instanceof ResponseError)
            {
                LOG.info("Sending sms to fail");
                LOG.info("The error caused by the bad content of SMS");
                String code = ((ResponseError) result).getCode();
                LOG.error("Error Code : "+code+" [ID SMS : "+id+"]");
                String description = ((ResponseError) result).getDescription();
                LOG.error("Error Description : "+description+" [ID SMS : "+id+"]");
                String message = ((ResponseError) result).getMessage();
                LOG.error("Error Message : "+message+" [ID SMS : "+id+"]");
                return SmsSent.SentErrorStatus.RESPONSE_ERROR;
            }
        }
        LOG.info("The sending of the SMS returns an empty result");
        return null;
    }

    public SmsSent saveSentSms(SMS sms,String receiverType,long receiverId,long userId,SmsSent.SentErrorStatus errorStatus)
    {
        User user = userService.find(userId);
        if(user == null)
        {
            LOG.info("User Id is not exist");
            return null;
        }
        if(receiverTypeIsCompany(receiverType))
        {
            Company company = companyService.find(receiverId);
            return smsSentService.create(new SmsSent(user,sms,company,null,null, LocalDateTime.now(), errorStatus));
        }
        else if(receiverTypeIsCustomer(receiverType))
        {
            Customer customer = customerService.find(receiverId);
            return smsSentService.create(new SmsSent(user,sms,null,customer,null,LocalDateTime.now(),errorStatus));
        }
        else if(receiverTypeIsPartner(receiverType))
        {
            Partner partner = partnerService.find(receiverId);
            return smsSentService.create(new SmsSent(user,sms,null,null,partner,LocalDateTime.now(), errorStatus));
        }
        LOG.info("The Id Receiver is not real a Receiver");
        return null;
    }

    private Optional<Object> sendSms(String senderAddress,String senderName,String receiver,String content,String country)
    {
        boolean oSmsIsConfigure = SMSOrangeAccessService.isConfigure();
        if(oSmsIsConfigure)
        {
            CountryCode countryCode = countryCode(country);
            OrangeSMS sms;
            sms = new OrangeSMS(receiver, senderAddress, senderName, content, countryCode);
            try
            {
                OSms oSms =  SMSOrangeAccessService.getOSms();
                ResponseSMS responseSMS = oSms.sendSms(sms);
                LOG.info("SMS has been sent");
                return Optional.of(responseSMS);
            }
            catch(IOException e)
            {
                e.printStackTrace();
                throw new ServiceIoConnectivityException();
            }
            catch(HttpApiOrangeException e)
            {
                if(e.errorIsService())
                {
                    ServiceException serviceException = e.getServiceException();
                    String messageId = serviceException.getMessageId();
                    LOG.error(messageId);
                    String text = serviceException.getText();
                    LOG.error(text);
                    serviceException.getVariables()
                            .parallelStream()
                            .forEach(LOG::error);
                    return Optional.of(serviceException);
                }
                else
                {
                    ResponseError responseError = e.getResponseError();
                    LOG.error("Code Error : "+responseError.getCode());
                    LOG.error("Description Error : "+responseError.getDescription());
                    LOG.error("Message Error : "+responseError.getMessage());
                    return Optional.of(responseError);
                }
            }
        }
        LOG.info("Orange Service is not configure");
        return Optional.empty();
    }

    private CountryCode countryCode(String country)
    {
        country = country.toUpperCase();
        if(country.equals(CountryCode.botswana.getCountry().toUpperCase()))
            return CountryCode.botswana;
        if(country.equals(CountryCode.cameroon.getCountry().toUpperCase()))
            return CountryCode.cameroon;
        if(country.equals(CountryCode.egypt.getCountry().toUpperCase()))
            return CountryCode.egypt;
        if(country.equals(CountryCode.guineaConakry.getCountry().toUpperCase()))
            return CountryCode.guineaConakry;
        if(country.equals(CountryCode.ivoryCoast.getCountry().toUpperCase()))
            return CountryCode.ivoryCoast;
        if(country.equals(CountryCode.mali.getCountry().toUpperCase()))
            return CountryCode.mali;
        if(country.equals(CountryCode.niger.getCountry().toUpperCase()))
            return CountryCode.niger;
        if(country.equals(CountryCode.RDCongo.getCountry().toUpperCase()))
            return CountryCode.RDCongo;
        return CountryCode.senegal;
    }

    private boolean receiverTypeIsCustomer(String type)
    {
        return type.toUpperCase().equals("CUSTOMER");
    }

    private boolean receiverTypeIsCompany(String type)
    {
        return type.toUpperCase().equals("COMPANY");
    }

    private boolean receiverTypeIsPartner(String type)
    {
        return type.toUpperCase().equals("PARTNER");
    }

}
