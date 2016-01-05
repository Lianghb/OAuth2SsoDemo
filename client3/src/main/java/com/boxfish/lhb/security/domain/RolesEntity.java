package com.boxfish.lhb.security.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by boxfish on 15/12/19.
 */
@Table(name="roles")
@Entity
public class RolesEntity {
    private String role;
    private Collection<AuthoritiesEntity> authoritiesByRole;

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

        RolesEntity that = (RolesEntity) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }

    @OneToMany(mappedBy = "rolesByRole")
    public Collection<AuthoritiesEntity> getAuthoritiesByRole() {
        return authoritiesByRole;
    }

    public void setAuthoritiesByRole(Collection<AuthoritiesEntity> authoritiesByRole) {
        this.authoritiesByRole = authoritiesByRole;
    }
}
