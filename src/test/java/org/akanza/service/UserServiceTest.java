package org.akanza.service;

import org.akanza.model.Authority;
import org.akanza.model.User;
import org.akanza.repository.AuthorityRepository;
import org.akanza.repository.UserRepository;
import org.akanza.utils.AuthorityValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest
{

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    UserRepository service;
    @Autowired
    AuthorityRepository authorityRepository;

    @Before
    public void setup()
    {
        Authority authority1 = authorityRepository.save(AuthorityValue.getAuthority(AuthorityValue.SuperAdmin, AuthorityValue.Admin));
        Authority authority2 = authorityRepository.save(AuthorityValue.getAuthority(AuthorityValue.SuperAdmin, AuthorityValue.Admin,AuthorityValue.User));
        entityManager.persistAndFlush(new User("login","pass","name","name","", LocalDate.now(),"1",null,"semail@gmail.com"
                ,null,authority1));
        entityManager.persist(new User("halo","pass","bili","poli","", LocalDate.now(),"2",null,"pemail@gmail.com"
                ,null,authority2));
    }

    @Test
    public void find() throws Exception
    {
        List<User> users = service.findAll();
        assertFalse(users.isEmpty());
    }

    @Test
    public void find1() throws Exception
    {
        User user = service.findOne(1L);
        assertNotNull(user);
    }

}
