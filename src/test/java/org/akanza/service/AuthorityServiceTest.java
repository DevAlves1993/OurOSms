package org.akanza.service;

import org.akanza.model.Authority;
import org.akanza.repository.AuthorityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorityServiceTest
{
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    AuthorityRepository repository;

    @Test
    public void authorities() throws Exception
    {
        Authority authority = entityManager.persist(new Authority("Test 1", "Test 2"));
        String authorities = authority.getAuthority();
        System.out.println(authorities);
    }

    @Test
    public void create() throws Exception
    {
        Authority au = repository.save(new Authority("Test A", "Test B"));
        assertNotNull(au);
    }

    @Test
    public void update() throws Exception
    {
        Authority au = repository.save(new Authority("Test A", "Test B"));
        System.out.println(au.getAuthority());
        au.setAuthority(AuthorityUtils.createAuthorityList("Test C","Test D"));
        repository.save(au);
        System.out.println(au.getAuthority());
        assertTrue(au.getAuthority().equals("Test C, Test D"));
    }

    @Test
    public void delete() throws Exception
    {
        Authority au = repository.save(new Authority("Test A", "Test B"));
        au = repository.findOne(au.getId());
        assertNotNull(au);
    }

}
