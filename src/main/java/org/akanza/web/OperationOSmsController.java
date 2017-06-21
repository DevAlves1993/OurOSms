package org.akanza.web;

import org.akanza.service.SMSOrangeAccessService;
import org.akanza.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RestController
@RequestMapping("/api/v1/operation")
public class OperationOSmsController
{
    @Autowired
    private SMSOrangeAccessService service;
    @Autowired
    private TokenUtils utils;

    @PostMapping(value = "/osms")
    private ResponseEntity operate(@RequestBody String token)
    {
        String secretId = utils.getSecretId(token);
        String secretKey = utils.getSecretKey(token);
        boolean isInit = service.writeAccessOrange(secretId, secretKey);
        if(isInit)
            return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
