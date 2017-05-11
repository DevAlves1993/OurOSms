package org.akanza.utils;

import org.akanza.model.Authority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public static Authority getAuthority(AuthorityValue... value)
    {
        List<AuthorityValue> list = Arrays.asList(value);
        String[] s = new String[list.size()];
        final int[] counter = {0};
        list.stream()
            .map(authorityValue -> authorityValue.value)
            .forEach(val -> {
                s[counter[0]] = val;
                counter[0]++;
            });

        return new Authority((String[])s);
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
