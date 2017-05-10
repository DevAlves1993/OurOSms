package org.akanza.service;

import org.akanza.model.Company;
import org.akanza.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Christian Amani on 09/05/2017.
 */
@Service
public class CompanyService
{
    private final Logger LOG = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository repository;


    public Company find(long id)
    {
        return repository.findOne(id);
    }

    public List<Company> find()
    {
        return repository.findAll();
    }

    public Company save(Company company)
    {
        company = repository.save(company);
        LOG.info("Company was created");
        return company;
    }

    public Company update(Company company)
    {
        company = repository.save(company);
        LOG.info("Company was updated");
        return company;
    }
}
