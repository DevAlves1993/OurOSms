package org.akanza.service;

import org.akanza.model.Authority;
import org.akanza.repository.AuthorityRepository;
import org.akanza.utils.AuthorityValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@Service
public class AuthorityService
{
    private final Logger LOG = LoggerFactory.getLogger(AuthorityService.class);

    @Autowired
    private AuthorityRepository repository;

    public List<String> authorities()
    {
        return AuthorityValue.allAuthorities();
    }

    public Authority create(Authority authority)
    {
        authority = repository.save(authority);
        LOG.info("Authority was created");
        return authority;
    }

    public Authority update(Authority authority)
    {
        authority = repository.save(authority);
        LOG.info("Authority was updated");
        return authority;
    }

    public void delete(long id)
    {
        repository.delete(id);
        LOG.info("Authority was delete");
    }
}
