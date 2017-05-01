package org.akanza.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Created by Christian Amani on 01/05/2017.
 */
public class UserAuthentication extends AbstractAuthenticationToken
{

    private String login;
    private String password;

    public UserAuthentication(List<GrantedAuthority> authorities,String login,String password)
    {
        super(authorities);
        this.login = login;
        this.password = password;
    }

    @Override
    public Object getCredentials()
    {
        return password;
    }

    @Override
    public Object getPrincipal()
    {
        return login;
    }
}
