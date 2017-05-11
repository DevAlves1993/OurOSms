package org.akanza.service;

import org.akanza.config.AppConfiguration;
import org.akanza.entity.AuthenticationRequest;
import org.akanza.entity.AuthenticationResponse;
import org.akanza.model.Authority;
import org.akanza.model.User;
import org.akanza.repository.AuthorityRepository;
import org.akanza.repository.UserRepository;
import org.akanza.utils.TokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class AccountServiceTest
{

    @Autowired
    AuthorityRepository repository;
    @Autowired
    AccountService service;

    @Test
    public void authenticate() throws Exception
    {
        Authority authority = repository.save(new Authority("Test 1", "Test 2"));
        service.create(new User("login1","password","name","name","content", LocalDate.now(),"number1",null
                ,"emai1@gmail.com",null,authority));
        AuthenticationRequest request = new AuthenticationRequest();
        request.setLogin("login1");
        request.setPassword("password");
        AuthenticationResponse response = service.authenticate(request);
        assertNotNull(response);
        System.out.println(response.getFirstName());
        System.out.println(response.getLastName());
        System.out.println(response.getToken());
        System.out.println(response.getUserId());
        System.out.println(response.getWords());
    }

    @Test
    public void create() throws Exception
    {
        Authority authority = repository.save(new Authority("Test 1", "Test 2"));
        User user = service.create(new User("login2", "password", "name", "name", "content", LocalDate.now(), "number2", null
                , "emai2@gmail.com", null, authority));
        assertNotNull(user);
    }

    @Test
    public void resetPassword() throws Exception
    {
        Authority authority = repository.save(new Authority("Test 1", "Test 2"));
        User user = service.create(new User("login3", "password", "name", "name", "content", LocalDate.now(), "number3", null
                , "emai3@gmail.com", null, authority));
        String password = user.getPassword();
        System.out.println(password);
        user = service.resetPassword(user.getId(), "password", "newPassword");
        String newPassword = user.getPassword();
        assertFalse(password.equals(newPassword));
        System.out.println("newPassword");
    }

    @Test
    public void update() throws Exception
    {
        Authority authority = repository.save(new Authority("Test 1", "Test 2"));
        User user = service.create(new User("login4", "password", "name", "name", "content", LocalDate.now(), "number4", null
                , "emai4@gmail.com", null, authority));
        System.out.println(user.getCreated().toString());
        String password = user.getPassword();
        System.out.println(password);
        user.setCreated(LocalDate.now().plusYears(1));
        System.out.println(user.getCreated().toString());
        user = service.update(user);
        String newPassword = user.getPassword();
        System.out.println(newPassword);
    }

    @Test
    public void delete() throws Exception
    {
        Authority authority = repository.save(new Authority("Test 1", "Test 2"));
        User user = service.create(new User("login5", "password", "name", "name", "content", LocalDate.now(), "number4", null
                , "emai5@gmail.com", null, authority));
        service.delete(user.getId());
    }

}
