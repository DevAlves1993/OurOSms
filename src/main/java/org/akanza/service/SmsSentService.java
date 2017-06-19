package org.akanza.service;

import org.akanza.model.SmsSent;
import org.akanza.repository.SmsSentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Christian Amani on 18/06/2017.
 */
@Service
public class SmsSentService
{
    private final Logger LOG = LoggerFactory.getLogger(SmsSentService.class);
    @Autowired
    private SmsSentRepository repository;

    public SmsSent create(SmsSent smsSent)
    {
        return repository.save(smsSent);
    }

    public List<SmsSent> create(List<SmsSent> list)
    {
        return repository.save(list);
    }

    public List<SmsSent> find()
    {
        return repository.findAll();
    }

    public SmsSent find(long id)
    {
        return repository.findOne(id);
    }
}
