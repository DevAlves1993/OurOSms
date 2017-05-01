package org.akanza.utils;

/**
 * Created by Christian Amani on 01/05/2017.
 */
public enum  AuthorityValue
{
    SuperAdmin("SUPER_ADMIN"),
    Admin("ADMIN"),
    User("USER");

    String value;
    AuthorityValue(String val)
    {
        this.value = val;
    }
    String getValue()
    {
        return value;
    }
}
