package org.akanza.service;

import org.akanza.model.SMS;
import org.akanza.repository.SMSRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SmsServiceTest
{

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    SMSRepository repository;

    @Test
    public void find() throws Exception
    {
        entityManager.persist(new SMS("+225746647","Content 1","Maho","+225474546","FR"));
        entityManager.persist(new SMS("+225746647","Content 1","Maho","+225474546","FR"));
        List<SMS> list = repository.findAll();
        assertFalse(list.isEmpty());
    }

    @Test
    public void find1() throws Exception
    {
        entityManager.persist(new SMS("+225746647","Content 1","Maho","+225474546","FR"));
        entityManager.persist(new SMS("+225746647","Content 1","Maho","+225474546","FR"));
        SMS sms = repository.findOne(7L);
        assertNotNull(sms);
    }

    @Test
    public void save() throws Exception
    {
        repository.save(new SMS("+225746647","Content 3","Maho","+225474546","FR"));
        repository.save(new SMS("+225746647","Content 4","Maho","+225474546","FR"));
        List<SMS> list = repository.findAll();
        assertFalse(list.isEmpty());
    }

    @Test
    public void update() throws Exception
    {
        SMS sms = repository.save(new SMS("+225746647", "Content 3", "Maho", "+225474546", "FR"));
        assertNotNull(sms);
        sms.setContent("Content 33");
        sms = repository.save(sms);
        assertNotNull(sms);
        System.out.println(sms.getContent());
    }

    @Test
    public void delete() throws Exception
    {
        entityManager.persist(new SMS("+225746647","Content 1","Maho","+225474546","FR"));
        List<SMS> list = repository.findAll();
        assertFalse(list.isEmpty());
        list.forEach((sms) -> repository.delete(sms));
        List<SMS> newList = repository.findAll();
        assertTrue(newList.isEmpty());
    }

}
