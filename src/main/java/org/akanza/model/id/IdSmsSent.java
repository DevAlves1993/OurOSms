package org.akanza.model.id;

import java.io.Serializable;

/**
 * Created by Christian Amani on 03/05/2017.
 */
public class IdSmsSent implements Serializable
{
    private Long company;
    private Long customer;
    private Long partner;

    public IdSmsSent()
    {}

    public IdSmsSent(Long company, Long customer, Long partner)
    {
        this.company = company;
        this.customer = customer;
        this.partner = partner;
    }

    public Long getCompany()
    {
        return company;
    }

    public void setCompany(Long company)
    {
        this.company = company;
    }

    public Long getCustomer()
    {
        return customer;
    }

    public void setCustomer(Long customer)
    {
        this.customer = customer;
    }

    public Long getPartner()
    {
        return partner;
    }

    public void setPartner(Long partner)
    {
        this.partner = partner;
    }
}
