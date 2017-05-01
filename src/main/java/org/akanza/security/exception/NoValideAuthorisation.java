package org.akanza.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Christian Amani on 01/05/2017.
 */
public class NoValideAuthorisation extends AuthenticationException
{
    public NoValideAuthorisation()
    {
        super("Token is Not Valid");
    }
}
