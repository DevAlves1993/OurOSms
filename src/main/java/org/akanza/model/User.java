package org.akanza.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Entity
@Table(name = "user")
public class User implements UserDetails,Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "login",unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "prenom")
    private String firstName;
    @Column(name = "nom")
    private String lastName;
    @Column(name = "libelle", length = 1000)
    private String words;
    @Column(name = "créer")
    private LocalDate created;
    @Column(name = "numero_1",unique = true)
    private String numberPhone1;
    @Column(name = "numero_2",unique = true)
    private String numberPhone2;
    @Column(name = "mail_1",unique = true)
    @Email
    private String email1;
    @Column(name = "mail_2",unique = true)
    private String email2;
    @ManyToOne(optional = false)
    @JoinColumn(name = "authority_id",foreignKey = @ForeignKey(name = "FK_user_authority"))
    private Authority authority;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SmsSent> listSms = new ArrayList<>();

    public User()
    {
        // Not Implemented
    }

    public User(String login,String password,String firstName,String lastName,String words,LocalDate created
            ,String numberPhone1,String numberPhone2,String email1,String email2,Authority authority)
    {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.words = words;
        this.created = created;
        this.numberPhone1 = numberPhone1;
        this.numberPhone2 = numberPhone2;
        this.email1 = email1;
        this.email2 = email2;
        this.authority = authority;
    }

    public User(String firstName,String lastName,String words,LocalDate created
            ,String numberPhone1,String numberPhone2,String email1,String email2,Authority authority)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.words = words;
        this.created = created;
        this.numberPhone1 = numberPhone1;
        this.numberPhone2 = numberPhone2;
        this.email1 = email1;
        this.email2 = email2;
        this.authority = authority;
    }

    public long getId()
    {
        return id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public Authority getAuthority()
    {
        return authority;
    }

    public void setAuthority(Authority authority)
    {
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        String authorities = this.authority.getAuthority();
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return login;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return false;
    }

    @Override
    public boolean isEnabled()
    {
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(login,password,firstName,lastName,words,created,numberPhone1,numberPhone2,email1,email2
                ,authority);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return Objects.equals(login,user.login) &&
                Objects.equals(password,user.password) &&
                Objects.equals(firstName,user.firstName) &&
                Objects.equals(words,user.words) &&
                Objects.equals(created,user.created) &&
                Objects.equals(numberPhone1,user.numberPhone1) &&
                Objects.equals(numberPhone2,user.numberPhone2) &&
                Objects.equals(email1,user.email1) &&
                Objects.equals(email2,user.email2) &&
                Objects.equals(authority,user.authority);
    }
}
