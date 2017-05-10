package org.akanza.service;

import org.akanza.model.Customer;
import org.akanza.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@Service
public class CustomerService
{
    private final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository repository;

    public Customer find(long id)
    {
        return repository.findOne(id);
    }

    public List<Customer> find()
    {
        return repository.findAll();
    }

    public Customer save(Customer customer)
    {
        customer = repository.save(customer);
        LOG.info("Customer was created");
        return customer;
    }

    public Customer update(Customer customer)
    {
        customer = repository.save(customer);
        LOG.info("Customer was updated");
        return customer;
    }

    public void delete(long id)
    {
        repository.delete(id);
    }
}
