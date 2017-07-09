package org.akanza.config;

import org.akanza.security.*;
import org.akanza.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

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

    @Autowired
    private UserProvider provider;

    @Autowired
    private AuthenticationEntryError entryError;

    @Autowired
    private AccountService accountService;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception
    {
        return new ProviderManager(Collections.singletonList(provider));
    }

    @Bean
    public AuthorisationTokenFilter authorisationTokenFilter() throws Exception
    {
        AuthorisationTokenFilter authorisationTokenFilter = new AuthorisationTokenFilter();
        authorisationTokenFilter.setAuthenticationManager(authenticationManager());
        authorisationTokenFilter.setAuthenticationSuccessHandler(new AuthenticationSuccess());
        return authorisationTokenFilter;
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers( "/resources/**", "/static/**", "/public/**"
                , "/webui/**", "/*.html", "/**/*.html" ,"/**/*.css","/**/*.js"
                ,"/**/*.png","/**/*.jpg", "/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf","/**/*.woff"
                ,"/swagger-resources/**","/v2/api-docs/**","/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**")
                    .permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth")
                    .permitAll()
                .antMatchers(HttpMethod.GET,"/")
                    .permitAll()
                .anyRequest()
                    .authenticated()
            .and()
            .addFilterBefore(new AuthenticationTokenFilter(accountService), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(authorisationTokenFilter(),UsernamePasswordAuthenticationFilter.class)
            .headers()
                .cacheControl().disable()
            .and()
            .csrf()
                .disable()
            .cors()
                .configurationSource(corsConfig)
            .and()
            .exceptionHandling()
                .authenticationEntryPoint(entryError);
    }
}
