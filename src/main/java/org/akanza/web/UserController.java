package org.akanza.web;

import org.akanza.model.User;
import org.akanza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Christian Amani on 09/05/2017.
 */
@RestController
@RequestMapping("/api/users")
public class UserController
{
    
    @Autowired
    private UserService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> findAll()
    {
        List<User> list = service.find();
        if(list.isEmpty())
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<User>>(list,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> find(@PathVariable long id)
    {
        User user = service.find(id);
        if(user != null)
            return new ResponseEntity<User>(user,HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
}
