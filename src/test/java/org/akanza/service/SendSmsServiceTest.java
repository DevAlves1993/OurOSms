package org.akanza.service;

import org.akanza.repository.SMSRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 17/06/2017.
 */
// TODO : This class will be tested later when access to an orange account will be available
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SMSOrangeAccessService.class,SmsService.class, SMSRepository.class})
public class SendSmsServiceTest
{
    @Test
    public void saveAndSent() throws Exception
    {
    }

}
