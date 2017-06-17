package org.akanza.service;

import org.akanza.config.AppConfiguration;
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
@SpringBootTest(classes = {AppConfiguration.class,SMSOrangeAccessService.class})
public class SMSOrangeAccessServiceTest
{

    @Autowired
    private SMSOrangeAccessService service;


    @Test
    public void configServiceOrange()
    {
        // TODO : This class will be tested later when access to an orange account will be available
    }

    @Test
    public void readAccessOrange() throws Exception
    {
        service.readAccessOrange();
        String clientId = SMSOrangeAccessService.getClientId();
        assertNotNull(clientId);
        System.out.println(clientId);
        String secretCode = SMSOrangeAccessService.getSecretKey();
        assertNotNull(secretCode);
        System.out.println(secretCode);
    }

    @Test
    public void writeAccessOrange() throws Exception
    {
        boolean b = service.writeAccessOrange("clientId", "secretKey");
        assertTrue(b);
    }

    @Test
    public void writeAndReadAccessOrange() throws Exception
    {
        boolean b = service.writeAccessOrange("clientId", "secretKey");
        assertTrue(b);
        service.readAccessOrange();
        assertNotEquals("",SMSOrangeAccessService.getClientId());
        assertNotEquals("",SMSOrangeAccessService.getSecretKey());
    }

    @Test
    public void getClientId() throws Exception
    {
        service.readAccessOrange();
        String clientId = service.getClientId();
        assertNotNull(clientId);
        System.out.println(clientId);
    }

    @Test
    public void getSecretKey() throws Exception
    {
        String secretKey = service.getSecretKey();
        assertNotNull(secretKey);
        System.out.println(secretKey);
    }

}
