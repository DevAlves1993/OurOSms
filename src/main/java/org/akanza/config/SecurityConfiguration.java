package org.akanza.config;

import org.akanza.security.AuthenticationEntryError;
import org.akanza.security.AuthenticationSuccess;
import org.akanza.security.AuthenticationTokenFilter;
import org.akanza.security.UserProvider;
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

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception
    {
        return new ProviderManager(Collections.singletonList(provider));
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilter() throws Exception
    {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        authenticationTokenFilter.setAuthenticationSuccessHandler(new AuthenticationSuccess());
        return authenticationTokenFilter;
    }

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
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**")
                    .permitAll()
                .antMatchers("/api/auth")
                    .permitAll()
                .anyRequest()
                    .authenticated()
            .and()
            .addFilterBefore(authenticationTokenFilter(),AuthenticationTokenFilter.class)
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
