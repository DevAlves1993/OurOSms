package org.akanza.web;

import org.akanza.service.SMSOrangeAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Christian Amani on 10/05/2017.
 */
@RestController
@RequestMapping("/api/operation")
public class OperationOSmsController
{
    @Autowired
    private SMSOrangeAccessService service;

    @PostMapping(value = "/osms")
    private ResponseEntity operate(@RequestBody String token)
    {
        // TODO : implement later
        return null;
    }
}
