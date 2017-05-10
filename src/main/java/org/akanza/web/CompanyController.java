package org.akanza.web;

import org.akanza.model.Company;
import org.akanza.repository.CompanyRepository;
import org.akanza.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Christian Amani on 09/05/2017.
 */
@RestController
@RequestMapping("/api/companies")
public class CompanyController
{
    @Autowired
    private CompanyService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Company>> findAll()
    {
        List<Company> list = service.find();
        if(list.isEmpty())
            return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<List<Company>>(list,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Company> find(@PathVariable long id)
    {
        Company company = service.find(id);
        if(company == null)
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Company>(company,HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Company> save(@RequestBody Company company)
    {
        company = service.save(company);
        return new ResponseEntity<Company>(company,HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE}
            ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Company> update(@RequestBody Company company)
    {
        company = service.update(company);
        return new ResponseEntity<Company>(company,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id)
    {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
