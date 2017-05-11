package org.akanza.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Entity
@Table(name = "Authorité")
public class Authority
{
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "droit")
    private String authority;

    @Transient
    private List<GrantedAuthority> authorities;

    public Authority()
    {
        // Not implemented
    }

    public Authority(String... authority)
    {
        this.authority = "";
        this.authorities = AuthorityUtils.createAuthorityList(authority);
        setAuthority();
    }

    public String getAuthority()
    {
        return this.authority;
    }
    
    public void setAuthority(List<GrantedAuthority> authorities)
    {
        this.authorities = authorities;
        this.authority = "";
        setAuthority();
    }

    private void setAuthority()
    {
        authorities.forEach(grantedAuthority -> {
            String authority = grantedAuthority.getAuthority();
            if(this.authority.isEmpty())
                this.authority = authority;
            else
                this.authority += ", "+authority;
        });
    }


    public long getId()
    {
        return id;
    }
}
