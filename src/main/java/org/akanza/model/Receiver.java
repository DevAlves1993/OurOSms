package org.akanza.model;


import javax.persistence.*;


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
