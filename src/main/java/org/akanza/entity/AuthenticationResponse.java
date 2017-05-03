package org.akanza.entity;

/**
 * Created by Christian Amani on 03/05/2017.
 */
public class AuthenticationResponse
{
    private String token;
    private long userId;
    private String firstName;
    private String lastName;
    private String words;

    public AuthenticationResponse(String token, long userId, String firstName, String lastName, String words)
    {
        this.token = token;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.words = words;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getWords()
    {
        return words;
    }

    public void setWords(String words)
    {
        this.words = words;
    }
}
