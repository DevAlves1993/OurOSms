package org.akanza.config;

import io.github.devalves.osms.OSms;
import okhttp3.OkHttpClient;
import org.akanza.service.SMSOrangeAccessService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Configuration
public class AppConfiguration
{
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(16);
    }

    @Bean
    public OSms.BuilderOSms createOSms()
    {
        return new OSms.BuilderOSms()
                .id("")
                .secretCode("");
    }
}
