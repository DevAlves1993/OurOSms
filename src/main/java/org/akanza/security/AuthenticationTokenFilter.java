package org.akanza.security;

import org.akanza.security.exception.MissingAuthorisation;
import org.akanza.security.exception.NoValideAuthorisation;
import org.akanza.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.*;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Component
public class AuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter
{
    private Logger LOG = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    @Autowired
    private TokenUtils tokenUtils;

    public AuthenticationTokenFilter()
    {
        super(new OrRequestMatcher(new AntPathRequestMatcher("/api/auth/users")
                ,new AntPathRequestMatcher("api/auth/users/**")
                ,new AntPathRequestMatcher("api/v1/**")));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException
    {
        String header = request.getHeader("Authorisation");
        if( header == null || !header.startsWith("Baerer "))
        {
            LOG.info("Header Authorisation Missing");
            throw new MissingAuthorisation();
        }
        String authorisation = header.substring(7);
        if(tokenUtils.exist(authorisation))
        {
            List<GrantedAuthority> authority = tokenUtils.getAuthorityInToken(authorisation);
            String login = tokenUtils.getLoginInToken(authorisation);
            String password = tokenUtils.getLoginInToken(authorisation);
            UserAuthentication userAuthentication = new UserAuthentication(authority,login,password);
            return userAuthentication;
        }
        else
        {
            LOG.info("Authorisatio is Not Valid");
            throw new NoValideAuthorisation();
        }
    }
}
