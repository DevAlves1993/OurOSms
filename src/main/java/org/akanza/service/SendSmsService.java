package org.akanza.service;

import org.akanza.model.SMS;
import org.akanza.osms.OSms;
import org.akanza.osms.core.CountryCode;
import org.akanza.osms.core.exception.HttpApiOAuthOrangeException;
import org.akanza.osms.core.exception.HttpApiOrangeException;
import org.akanza.osms.model.OrangeSMS;
import org.akanza.osms.model.response.error.ServiceException;
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
    private static OSms oSms;
    private static boolean oSmsIsInit = false;
    @Autowired
    private SmsService service;

    public SMS saveAndSent(String senderAddress,Optional<String> senderName,String receiver,String content,String country)
    {
        boolean isSent = sendSms(senderAddress,senderName,receiver,content,country);
        if(isSent)
        {
            SMS sms;
            if(senderName.isPresent())
                sms = new SMS(receiver,content,senderName.get(),senderAddress,country);
            else
                sms = new SMS(receiver,content,"",senderAddress,country);
            sms = service.save(sms);
            return sms;
        }
        return null;
    }

    private boolean sendSms(String senderAddress,Optional<String> senderName,String receiver,String content,String country)
    {
        if(oSmsIsInit)
        {
            CountryCode countryCode = countryCode(country);
            OrangeSMS sms;
            if(senderName.isPresent())
                sms = new OrangeSMS(receiver,senderAddress,senderName.get(),content,countryCode);
            else
                sms = new OrangeSMS(receiver,senderAddress,content,countryCode);
            try
            {
                oSms.sendSms(sms);
                LOG.info("SMS has been sent");
                return true;
            }
            catch(IOException | HttpApiOrangeException e)
            {
                e.printStackTrace();
                if(e instanceof HttpApiOrangeException)
                {
                    if(((HttpApiOrangeException) e).errorIsService())
                    {
                        ServiceException serviceException = ((HttpApiOrangeException) e).getServiceException();
                        String messageId = serviceException.getMessageId();
                        LOG.error(messageId);
                        String text = serviceException.getText();
                        LOG.error(text);
                        serviceException.getVariables()
                                .parallelStream()
                                .forEach(LOG::error);
                    }
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

    public boolean initOSms(String clientId,String secretCode)
    {
        try
        {
            oSms = new OSms.BuilderOSms()
                    .id(clientId)
                    .secretCode(secretCode)
                    .build();
            oSmsIsInit = true;
            LOG.info("OSms has been build with success");
        }
        catch(IOException | HttpApiOAuthOrangeException e)
        {
            e.printStackTrace();
            if(e instanceof HttpApiOAuthOrangeException)
            {
                LOG.error(e.getMessage(),e);
                LOG.error(((HttpApiOAuthOrangeException) e).getDescription(),e);
            }
        }
        return oSmsIsInit;
    }
}
