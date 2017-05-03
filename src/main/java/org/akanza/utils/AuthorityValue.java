package org.akanza.utils;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> allAuthorities()
    {
        List<String> authorities = new ArrayList<>();
        authorities.add("SUPER_ADMIN");
        authorities.add("ADMIN");
        authorities.add("USER");
        return authorities;
    }
}
