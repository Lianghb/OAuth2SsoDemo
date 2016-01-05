package com.boxfish.lhb.security.domain;

import javax.persistence.*;

/**
 * Created by boxfish on 15/12/19.
 */
@Table(name="authorities")
@Entity
public class AuthoritiesEntity {

    private String id;
    private String username;
    private String role;
    private RolesEntity rolesByRole;
    private UsersEntity usersByUsername;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
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

        AuthoritiesEntity that = (AuthoritiesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "role", nullable = false, insertable = false, updatable = false)
    public RolesEntity getRolesByRole() {
        return rolesByRole;
    }

    public void setRolesByRole(RolesEntity rolesByRole) {
        this.rolesByRole = rolesByRole;
    }

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    public UsersEntity getUsersByUsername() {
        return usersByUsername;
    }

    public void setUsersByUsername(UsersEntity usersByUsername) {
        this.usersByUsername = usersByUsername;
    }
}
