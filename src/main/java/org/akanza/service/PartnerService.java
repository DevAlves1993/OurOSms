package org.akanza.service;

import org.akanza.model.Partner;
import org.akanza.repository.PartnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Christian Amani on 18/06/2017.
 */
@Service
public class PartnerService
{
    private final Logger LOG = LoggerFactory.getLogger(PartnerService.class);
    @Autowired
    private PartnerRepository repository;


    public Partner update(Partner partner)
    {
        if(partner == null)
            return null;
        return repository.save(partner);
    }

    public List<Partner> update(List<Partner> list)
    {
        if(list.isEmpty())
            return list;
        return repository.save(list);
    }

    public Partner find(long id)
    {
        return repository.findOne(id);
    }

    public List<Partner> find()
    {
        return repository.findAll();
    }
}
