package org.akanza.entity;

/**
 * Created by Christian Amani on 03/05/2017.
 */
public class AuthenticationRequest
{
    private String login;
    private String password;

    public AuthenticationRequest()
    {}

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
