package org.akanza.service;

import org.akanza.model.SMS;
import org.akanza.repository.SMSRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@Service
public class SmsService
{
    private final Logger LOG = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private SMSRepository repository;

    public SMS find(long id)
    {
        return repository.findOne(id);
    }

    public List<SMS> find()
    {
        return repository.findAll();
    }

    public SMS save(SMS sms)
    {
        sms = repository.save(sms);
        LOG.info("SMS was created");
        return sms;
    }

    public SMS update(SMS sms)
    {
        sms = repository.save(sms);
        LOG.info("SMS was update");
        return sms;
    }

    public void delete(long id)
    {
        repository.delete(id);
        LOG.info("SMS was deleted");
    }
}
