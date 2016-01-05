package com.boxfish.lhb.security.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by boxfish on 15/12/19.
 */


@Entity
@Table(name = "users")
public class UsersEntity {
    private String username;
    private String password;
    private Collection<AuthoritiesEntity> authoritiesByUsername;

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByUsername", fetch = FetchType.EAGER)
    public Collection<AuthoritiesEntity> getAuthoritiesByUsername() {
        return authoritiesByUsername;
    }

    public void setAuthoritiesByUsername(Collection<AuthoritiesEntity> authoritiesByUsername) {
        this.authoritiesByUsername = authoritiesByUsername;
    }
}
