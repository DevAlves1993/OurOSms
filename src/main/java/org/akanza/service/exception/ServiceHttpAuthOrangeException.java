package org.akanza.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Christian Amani on 17/06/2017.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Orange Identifier is not valid")
public class ServiceHttpAuthOrangeException extends RuntimeException
{}
