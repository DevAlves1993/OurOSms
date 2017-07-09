package org.akanza.service;

import io.github.devalves.osms.OSms;
import io.github.devalves.osms.core.exception.HttpApiOAuthOrangeException;
import org.akanza.service.exception.ServiceHttpAuthOrangeException;
import org.akanza.service.exception.ServiceIoConnectivityException;
import org.akanza.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@Service
public class SMSOrangeAccessService
{
    private final Logger LOG = LoggerFactory.getLogger(SMSOrangeAccessService.class);
    private final ClassPathResource pathResource = new ClassPathResource("static/orange-access.txt");

    @Autowired
    private OSms.BuilderOSms builderOSms;
    @Autowired
    private TokenUtils utils;

    @Value("${akanza.jwt.tokenForOrange}")
    private String orangeJwtKey;

    private static OSms oSms = null;
    private static String clientId = "";
    private static String secretKey = "";
    private static boolean oSmsIsConfigure = false;

    public void configServiceOrange()
    {
        readAccessOrange();
        try
        {
            oSms = builderOSms.id(clientId)
                    .secretCode(secretKey)
                    .build();
            oSmsIsConfigure = true;
            LOG.info("Orange Service is configured correctly");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            throw new ServiceIoConnectivityException();
        }
        catch(HttpApiOAuthOrangeException e)
        {
            e.printStackTrace();
            LOG.error(e.getMessage(),e);
            LOG.error(e.getDescription(),e);
            throw new ServiceHttpAuthOrangeException();
        }
    }

    public boolean save(String token)
    {
        String secretId = utils.getOrangeSecretId(token);
        String secretKey = utils.getOrangeSecretKey(token);
        return writeAccessOrange(secretId, secretKey);
    }

    public boolean writeAccessOrange(String clientId,String secretKey)
    {
        boolean writeAccessWithSuccess = false;
        try
        {
            File file = pathResource.getFile();
            System.out.println(file);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(clientId);
            writer.newLine();
            writer.write(secretKey);
            writer.flush();
            writer.close();
            writeAccessWithSuccess = true;
            LOG.info("Orange Access was well recorded");
            readAccessOrange();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            LOG.info("Registering Orange Accesses to Fail");
        }
        return writeAccessWithSuccess;
    }

    public void readAccessOrange()
    {
        try
        {
            InputStream inputStream = pathResource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            clientId = reader.readLine();
            if(clientId == null)
                clientId = "";
            secretKey = reader.readLine();
            if(secretKey == null)
                secretKey = "";
            LOG.info("Reading Orange Access Success");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            LOG.info("Reading Orange Access Failed");
        }
    }

    public String obtainOrangeJwtKey()
    {
        return orangeJwtKey;
    }

    public static String getClientId()
    {
        return clientId;
    }

    public static String getSecretKey()
    {
        return secretKey;
    }

    public static boolean isConfigure()
    {
        return oSmsIsConfigure;
    }

    public static OSms getOSms()
    {
        return oSms;
    }
}
