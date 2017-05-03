package org.akanza.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Entity
@Table(name = "partner")
public class Partner extends Receiver
{

    @Column(name = "nom",nullable = false)
    private String name;
    @Column(name = "url_web_site")
    private String urlWebSite;
    @Column(name = "libelle")
    private String words;
    @Column(name = "créer")
    private LocalDate created;
    @Column(name = "numero_1")
    private String numberPhone1;
    @Column(name = "numero_2")
    private String numberPhone2;
    @Column(name = "numero_3")
    private String numberPhone3;
    @Column(name = "mail_1")
    @Email
    private String email1;
    @Column(name = "mail_2")
    @Email
    private String email2;
    @Column(name = "siège")
    private String seat;
    @OneToMany(mappedBy = "partner",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SmsSent> listSms = new ArrayList<>();

    public Partner()
    {
        // Not Implemented
    }

    public Partner(String name, String urlWebSite, String words, LocalDate created, String numberPhone1,
            String numberPhone2, String numberPhone3, String email1, String email2, String seat)
    {
        this.name = name;
        this.urlWebSite = urlWebSite;
        this.words = words;
        this.created = created;
        this.numberPhone1 = numberPhone1;
        this.numberPhone2 = numberPhone2;
        this.numberPhone3 = numberPhone3;
        this.email1 = email1;
        this.email2 = email2;
        this.seat = seat;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrlWebSite()
    {
        return urlWebSite;
    }

    public void setUrlWebSite(String urlWebSite)
    {
        this.urlWebSite = urlWebSite;
    }

    public String getWords()
    {
        return words;
    }

    public void setWords(String words)
    {
        this.words = words;
    }

    public LocalDate getCreated()
    {
        return created;
    }

    public void setCreated(LocalDate created)
    {
        this.created = created;
    }

    public String getNumberPhone1()
    {
        return numberPhone1;
    }

    public void setNumberPhone1(String numberPhone1)
    {
        this.numberPhone1 = numberPhone1;
    }

    public String getNumberPhone2()
    {
        return numberPhone2;
    }

    public void setNumberPhone2(String numberPhone2)
    {
        this.numberPhone2 = numberPhone2;
    }

    public String getNumberPhone3()
    {
        return numberPhone3;
    }

    public void setNumberPhone3(String numberPhone3)
    {
        this.numberPhone3 = numberPhone3;
    }

    public String getEmail1()
    {
        return email1;
    }

    public void setEmail1(String email1)
    {
        this.email1 = email1;
    }

    public String getEmail2()
    {
        return email2;
    }

    public void setEmail2(String email2)
    {
        this.email2 = email2;
    }

    public String getSeat()
    {
        return seat;
    }

    public void setSeat(String seat)
    {
        this.seat = seat;
    }

    public List<SmsSent> getListSms()
    {
        return listSms;
    }
}
