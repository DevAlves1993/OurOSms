package org.akanza.web;

import org.akanza.entity.AuthenticationRequest;
import org.akanza.entity.AuthenticationResponse;
import org.akanza.model.User;
import org.akanza.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Christian Amani on 03/05/2017.
 */
@RestController
@RequestMapping("api/auth")
public class AccountController
{
    @Autowired
    private AccountService service;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authentication(AuthenticationRequest authentication)
    {
        if(authentication != null &&
                authentication.getLogin() != null && authentication.getPassword() != null)
        {
            AuthenticationResponse response = service.authenticate(authentication);
            if(response != null)
                return new ResponseEntity<>(response, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/users",consumes = {MediaType.APPLICATION_JSON_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> createUser(User user)
    {
        if(user != null)
        {
            user = service.create(user);
            return new ResponseEntity<User>(user,HttpStatus.CREATED);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> updateUser(@PathVariable long id,User user)
    {
        if(user != null)
        {
            user = service.update(user);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable long id)
    {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
