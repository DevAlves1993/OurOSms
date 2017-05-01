package org.akanza.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CorsConfig corsConfig;

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/", "/resources/**", "/static/**", "/public/**", "/webui/**", "/h2-console/**"
                , "/*.html", "/**/*.html" ,"/**/*.css","/**/*.js","/**/*.png","/**/*.jpg", "/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf","/**/*.woff");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .cors()
                .configurationSource(corsConfig)
            .and()
            .authorizeRequests()
                .anyRequest()
                    .authenticated();
    }
}
