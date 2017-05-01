package org.akanza.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian Amani on 01/05/2017.
 */
public abstract class Receiver
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SmsSent> listSms = new ArrayList<>();

    public long getId()
    {
        return id;
    }

    public List<SmsSent> getListSms()
    {
        return listSms;
    }
}
