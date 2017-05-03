package org.akanza.service;

import org.akanza.entity.AuthenticationObject;
import org.akanza.model.User;
import org.akanza.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Christian Amani on 02/05/2017.
 */
@Service
public class AccountService
{

    private final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User authenticate(AuthenticationObject authentication)
    {
        String login = authentication.getLogin();
        User user = repository.findByLogin(login);
        if(user != null)
        {
            String password = authentication.getPassword();
            if(passwordEncoder.matches(password,user.getPassword()))
                return user;
        }
        return null;
    }


    public User create(User user)
    {
        String password = user.getPassword();
        String encodedPassword = encodedPassword(password);
        user.setPassword(encodedPassword);
        User newUser = repository.save(user);
        LOG.info("New user was created");
        return newUser;
    }

    public User resetPassword(long id,String oldPassword,String newPassword)
    {
        User user = repository.findOne(id);
        if(user != null)
        {
            String passwordEncoded = user.getPassword();
            if(passwordEncoder.matches(oldPassword,passwordEncoded))
            {
                String newPasswordEncoded = passwordEncoder.encode(newPassword);
                user.setPassword(newPasswordEncoded);
                User newUser = repository.save(user);
                LOG.info("Password of user was update");
                return newUser;
            }
        }
        LOG.info("Changing the user's password to fail");
        return null;
    }

    public User update(User newUser)
    {
        String password = newUser.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        newUser = repository.save(newUser);
        LOG.info("User was updated");
        return newUser;
    }

    public void delete(long id)
    {
        repository.delete(id);
    }

    private String encodedPassword(String password)
    {
        return passwordEncoder.encode(password);
    }
}
