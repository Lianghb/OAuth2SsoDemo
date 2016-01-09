package com.boxfish.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by boxfish on 15/12/19.
 */
@Table(name="auth_role")
@Entity
public class Role implements Serializable{
    private String role;
    private Collection<Authority> authoritiesByRole;

    @Id
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role that = (Role) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }

    @OneToMany(mappedBy = "rolesByRole")
    public Collection<Authority> getAuthoritiesByRole() {
        return authoritiesByRole;
    }

    public void setAuthoritiesByRole(Collection<Authority> authoritiesByRole) {
        this.authoritiesByRole = authoritiesByRole;
    }
}
