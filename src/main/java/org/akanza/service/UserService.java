package org.akanza.service;

import org.akanza.model.User;
import org.akanza.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@Service
public class UserService
{

    @Autowired
    private UserRepository repository;


    public User find(long id)
    {
        return repository.findOne(id);
    }

    public List<User> find()
    {
        return repository.findAll();
    }
}
