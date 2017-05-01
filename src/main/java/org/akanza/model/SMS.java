package org.akanza.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Entity
@Table(name = "Sms")
public class SMS implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "recepteur")
    private String receiver;
    // See link : https://fr.wikipedia.org/wiki/Short_Message_Service#Caract.C3.A9ristiques_techniques
    @Column(name = "contenu",length = 144)
    private String content;
    @Column(name = "nom_emetteur")
    private String senderName;
    @Column(name = "numero_emetteur")
    private String senderAddress;
    @Column(name = "pays")
    private String country;

    @OneToMany(mappedBy = "sms",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SmsSent> listSms = new ArrayList<>();

    public SMS()
    {
        // Not implemented
    }

    public SMS(String receiver, String content, String senderName, String senderAddress, String country)
    {
        this.receiver = receiver;
        this.content = content;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.country = country;
    }

    public long getId()
    {
        return id;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getSenderName()
    {
        return senderName;
    }

    public void setSenderName(String senderName)
    {
        this.senderName = senderName;
    }

    public String getSenderAddress()
    {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress)
    {
        this.senderAddress = senderAddress;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public List<SmsSent> getListSms()
    {
        return listSms;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(receiver,content,senderName,senderAddress,country);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        SMS sms = (SMS) obj;
        return Objects.equals(receiver,sms.receiver) &&
                Objects.equals(content,sms.content) &&
                Objects.equals(senderName,sms.senderName) &&
                Objects.equals(senderAddress,sms.senderAddress) &&
                Objects.equals(country,sms.country);
    }
}
