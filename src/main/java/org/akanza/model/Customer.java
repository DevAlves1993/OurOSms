package org.akanza.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Entity
@Table(name = "customer")
public class Customer extends Receiver
{
    @Column(name = "prenom")
    private String firstName;
    @Column(name = "nom")
    private String lastName;
    @Column(name = "créer")
    private LocalDate created;
    @Column(name = "libelle")
    private String words;
    @Column(name = "numero_1")
    private String numberPhone1;
    @Column(name = "numero_2")
    private String numberPhone2;
    @Column(name = "numero_3")
    private String numberPhone3;
    @Column(name = "mail_1")
    @Email
    private String mail1;
    @Column(name = "mail_2")
    @Email
    private String mail2;

    public Customer()
    {
        // Not implemented
    }

    public Customer(String firstName, String lastName, LocalDate created, String words, String numberPhone1,
            String numberPhone2, String numberPhone3, String mail1, String mail2)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
        this.words = words;
        this.numberPhone1 = numberPhone1;
        this.numberPhone2 = numberPhone2;
        this.numberPhone3 = numberPhone3;
        this.mail1 = mail1;
        this.mail2 = mail2;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public LocalDate getCreated()
    {
        return created;
    }

    public void setCreated(LocalDate created)
    {
        this.created = created;
    }

    public String getWords()
    {
        return words;
    }

    public void setWords(String words)
    {
        this.words = words;
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

    public String getMail1()
    {
        return mail1;
    }

    public void setMail1(String mail1)
    {
        this.mail1 = mail1;
    }

    public String getMail2()
    {
        return mail2;
    }

    public void setMail2(String mail2)
    {
        this.mail2 = mail2;
    }
}
