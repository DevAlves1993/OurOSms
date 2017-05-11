package org.akanza.service;

import org.akanza.model.Company;
import org.akanza.repository.CompanyRepository;
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
public class CompanyServiceTest
{

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    CompanyRepository repository;

    @Test
    public void find() throws Exception
    {
        entityManager.persist(new Company("company","url1","content", LocalDate.now(),"number1",null,null
                ,"email1@gmail.com",null,null,"seat"));
        entityManager.persist(new Company("company","url2","content", LocalDate.now(),"number2",null,null
                ,"email2@gmail.com",null,null,"seat"));
        List<Company> list = repository.findAll();
        assertFalse(list.isEmpty());
    }

    @Test
    public void find1() throws Exception
    {
        Company company = entityManager.persist(new Company("company", "url1", "content", LocalDate.now(), "number3", null, null
                , "email3@gmail.com", null, null, "seat"));
        company = repository.findOne(company.getId());
        assertNotNull(company);
    }

    @Test
    public void save() throws Exception
    {
        Company company = repository.save(new Company("company", "url1", "content", LocalDate.now(), "number4", null, null
                , "email4@gmail.com", null, null, "seat"));
        assertNotNull(company);
    }

    @Test
    public void update() throws Exception
    {
        Company company = repository.save(new Company("company", "url1", "content", LocalDate.now(), "number5", null, null
                , "email5@gmail.com", null, null, "seat"));
        company.setWords("Content True");
        company = repository.save(company);
        assertTrue(company.getWords().equals("Content True"));
    }

    @Test
    public void delete() throws Exception
    {
        Company company = repository.save(new Company("company", "url1", "content", LocalDate.now(), "number5", null, null
                , "email5@gmail.com", null, null, "seat"));
        repository.delete(company.getId());
    }

}
