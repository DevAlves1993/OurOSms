package org.akanza.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static String clientId = "";
    private static String secretKey = "";

    public boolean writeAccessOrange(String clientId,String secretKey)
    {
        boolean writeAccessWithSuccess = false;
        try
        {
            OutputStream outputStream = new FileOutputStream(pathResource.getFile());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(clientId);
            writer.newLine();
            writer.write(secretKey);
            writer.flush();
            writer.close();
            writeAccessWithSuccess = true;
            LOG.info("Orange Access was well recorded");
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
            secretKey = reader.readLine();
            LOG.info("Reading Orange Access Success");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            LOG.info("Reading Orange Access Failed");
        }
    }

    public String getClientId()
    {
        return clientId;
    }

    public String getSecretKey()
    {
        return secretKey;
    }
}
