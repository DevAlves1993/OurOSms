package org.akanza.config;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Component
public class CorsConfig implements CorsConfigurationSource
{
    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request)
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        return config;
    }
}
