package org.akanza.web;

import org.akanza.model.Customer;
import org.akanza.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController
{
    @Autowired
    private CustomerService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Customer>> findAll()
    {
        List<Customer> list = service.find();
        if(list.isEmpty())
            return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Customer>>(list,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> find(@PathVariable long id)
    {
        Customer customer = service.find(id);
        if(customer == null)
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Customer>(customer,HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> save(@RequestBody Customer customer)
    {
        customer = service.save(customer);
        return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Customer> update(@RequestBody Customer customer)
    {
        customer = service.update(customer);
        return new ResponseEntity<Customer>(customer,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id)
    {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
