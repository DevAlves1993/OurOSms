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
public class UserServiceTest
{

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AccountService service;

    @Test
    public void find() throws Exception
    {

    }

    @Test
    public void find1() throws Exception
    {

    }

}
