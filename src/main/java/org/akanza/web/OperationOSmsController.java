package org.akanza.web;

import org.akanza.service.SMSOrangeAccessService;
import org.akanza.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RestController
@RequestMapping("/api/v1/orange")
public class OperationOSmsController
{
    @Autowired
    private SMSOrangeAccessService service;


    @PostMapping(value = "/save")
    private ResponseEntity operate(@RequestBody String token)
    {
        boolean isSaving = service.save(token);
        if(isSaving)
            return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/keys")
    private ResponseEntity<String> getJwtOrangeKey()
    {
        String key = service.obtainOrangeJwtKey();
        if(key != null)
            return new ResponseEntity<String>(key,HttpStatus.OK);
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
