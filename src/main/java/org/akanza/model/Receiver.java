package org.akanza.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@MappedSuperclass
public abstract class Receiver
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    public long getId()
    {
        return id;
    }

}
