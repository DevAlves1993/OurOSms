package org.akanza.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SMSOrangeAccessService.class})
public class SMSOrangeAccessServiceTest
{

    @Autowired
    private SMSOrangeAccessService service;

    @Test
    public void writeAccessOrange() throws Exception
    {

    }

    @Test
    public void getClientId() throws Exception
    {

    }

    @Test
    public void getSecretKey() throws Exception
    {

    }

}
