package org.akanza.service;

import org.akanza.model.Customer;
import org.akanza.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceTest
{

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    CustomerRepository repository;

    @Test
    public void find() throws Exception
    {
        entityManager.persist(new Customer("name","name", LocalDate.now(),"content","1",null,null,"email@gmail.com"
                ,null));
        entityManager.persist(new Customer("name","name", LocalDate.now(),"content","2",null,null,"email1@gmail.com"
                ,null));
        List<Customer> list = repository.findAll();
        assertFalse(list.isEmpty());
    }

    @Test
    public void find1() throws Exception
    {
        Customer customer = entityManager.persist(new Customer("name", "name", LocalDate.now(), "content", "3", null, null, "email3@gmail.com"
                , null));
        customer = repository.findOne(customer.getId());
        assertNotNull(customer);
    }

    @Test
    public void save() throws Exception
    {
        Customer customer = repository.save(new Customer("name", "name", LocalDate.now(), "content", "4", null, null, "email4@gmail.com"
                , null));
        customer = repository.findOne(customer.getId());
        assertNotNull(customer);
    }

    @Test
    public void update() throws Exception
    {
        Customer customer = repository.save(new Customer("name", "name", LocalDate.now(), "content", "4", null, null, "email4@gmail.com"
                , null));
        System.out.println(customer.getCreated().toString());
        customer.setCreated(LocalDate.now().plusYears(1));
        customer = repository.save(customer);
        System.out.println(customer.getCreated().toString());
    }

    @Test
    public void delete() throws Exception
    {
        Customer customer = repository.save(new Customer("name", "name", LocalDate.now(), "content", "5", null, null, "email5@gmail.com"
                , null));
        repository.delete(customer.getId());
        customer = repository.findOne(customer.getId());
        assertNull(customer);
    }

}
