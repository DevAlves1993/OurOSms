package org.akanza.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTest
{

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AccountService service;

    @Test
    public void authenticate() throws Exception
    {

    }

    @Test
    public void create() throws Exception
    {

    }

    @Test
    public void resetPassword() throws Exception
    {

    }

    @Test
    public void update() throws Exception
    {

    }

    @Test
    public void delete() throws Exception
    {

    }

}
