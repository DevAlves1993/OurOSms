package org.akanza.service;

import io.github.devalves.osms.model.response.error.ResponseError;
import org.akanza.model.SMS;
import io.github.devalves.osms.OSms;
import io.github.devalves.osms.core.CountryCode;
import io.github.devalves.osms.core.exception.HttpApiOAuthOrangeException;
import io.github.devalves.osms.core.exception.HttpApiOrangeException;
import io.github.devalves.osms.model.OrangeSMS;
import io.github.devalves.osms.model.response.error.ServiceException;
import org.akanza.service.exception.ServiceHttpAuthOrangeException;
import org.akanza.service.exception.ServiceIoConnectivityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@Service
public class SendSmsService
{
    private final Logger LOG = LoggerFactory.getLogger(SendSmsService.class);
    @Autowired
    private SmsService service;

    public SMS saveAndSent(String senderAddress,Optional<String> senderName,String receiver,String content,String country)
    {
        boolean isSent = sendSms(senderAddress,senderName,receiver,content,country);
        if(isSent)
        {
            SMS sms;
            sms = senderName.map(s -> new SMS(receiver, content, s, senderAddress, country)).orElseGet(() -> new SMS(receiver, content, "", senderAddress, country));
            sms = service.save(sms);
            return sms;
        }
        return null;
    }

    private boolean sendSms(String senderAddress,Optional<String> senderName,String receiver,String content,String country)
    {
        boolean oSmsIsConfigure = SMSOrangeAccessService.isConfigure();
        if(oSmsIsConfigure)
        {
            CountryCode countryCode = countryCode(country);
            OrangeSMS sms;
            sms = senderName.map(s -> new OrangeSMS(receiver, senderAddress, s, content, countryCode)).orElseGet(() -> new OrangeSMS(receiver, senderAddress, content, countryCode));
            try
            {
                OSms oSms =  SMSOrangeAccessService.getOSms();
                oSms.sendSms(sms);
                LOG.info("SMS has been sent");
                return true;
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
                }
                else
                {
                    ResponseError responseError = e.getResponseError();
                    LOG.error("Code Error : "+responseError.getCode());
                    LOG.error("Description Error : "+responseError.getDescription());
                    LOG.error("Message Error : "+responseError.getMessage());
                }
            }
        }
        return false;
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

}
