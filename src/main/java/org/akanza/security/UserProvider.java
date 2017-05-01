package org.akanza.security;

import org.akanza.model.User;
import org.akanza.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Component
public class UserProvider implements AuthenticationProvider
{
    private final Logger LOG = LoggerFactory.getLogger(UserProvider.class);

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        UserAuthentication userAuthentication = (UserAuthentication) authentication;
        String login = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = repository.findByLogin(login);
        boolean isAuthenticated = verifyUser(user,password);
        LOG.info("User authentication value is : "+isAuthenticated);
        userAuthentication.setAuthenticated(isAuthenticated);
        return userAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return UserAuthentication.class.isAssignableFrom(authentication);
    }

    private boolean verifyUser(User user,String password)
    {
        LOG.info("Checking user access");
        if(user != null)
        {
            String encodedPassword = user.getPassword();
            return passwordEncoder.matches(password,encodedPassword);
        }
        return false;
    }
}
