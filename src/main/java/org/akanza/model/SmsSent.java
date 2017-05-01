package org.akanza.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Entity
@Table(name = "SmsSent")
public class SmsSent implements Serializable
{
    @Id
    @ManyToOne
    private Receiver receiver;

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private SMS sms;

    public SmsSent()
    {
        // Not implemented
    }

    public SmsSent(Receiver receiver, User user, SMS sms)
    {
        this.receiver = receiver;
        this.user = user;
        this.sms = sms;
    }

    public Receiver getReceiver()
    {
        return receiver;
    }

    public void setReceiver(Receiver receiver)
    {
        this.receiver = receiver;
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
        return Objects.hash(receiver,user,sms);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        SmsSent smsSent = (SmsSent) obj;
        return Objects.equals(this.receiver,smsSent.receiver) &&
                Objects.equals(this.user,smsSent.user) &&
                Objects.equals(this.sms,smsSent.sms);
    }
}
