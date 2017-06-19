package org.akanza.model;

import org.akanza.model.id.IdSmsSent;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Entity
@Table(name = "SmsSent")
@IdClass(IdSmsSent.class)
public class SmsSent implements Serializable
{
    @Id
    @ManyToOne
    private Company company;

    @Id
    @ManyToOne
    private Customer customer;

    @Id
    @ManyToOne
    private Partner partner;

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private SMS sms;

    @Column(name = "date_envoi")
    private LocalDateTime sentDate;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "type_envoi_erreur")
    private SentErrorStatus status;

    public SmsSent()
    {
        // Not implemented
    }

    public SmsSent(User user, SMS sms,Company company,Customer customer,Partner partner,LocalDateTime sentDate
            ,SentErrorStatus status)
    {
        this.company = company;
        this.customer = customer;
        this.partner = partner;
        this.user = user;
        this.sms = sms;
        this.sentDate = sentDate;
        this.status = status;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public SMS getSms()
    {
        return sms;
    }

    public void setSms(SMS sms)
    {
        this.sms = sms;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(company,customer,partner,user,sms);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        SmsSent smsSent = (SmsSent) obj;
        return Objects.equals(this.company,smsSent.company) &&
                Objects.equals(this.customer,smsSent.customer) &&
                Objects.equals(this.partner,partner) &&
                Objects.equals(this.user,smsSent.user) &&
                Objects.equals(this.sms,smsSent.sms);
    }

    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Partner getPartner()
    {
        return partner;
    }

    public void setPartner(Partner partner)
    {
        this.partner = partner;
    }

    public enum SentErrorStatus
    {
        NOTHING,
        SERVICE_ERROR,
        RESPONSE_ERROR;
    }
}
