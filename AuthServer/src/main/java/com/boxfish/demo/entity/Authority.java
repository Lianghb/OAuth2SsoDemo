package com.boxfish.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by boxfish on 15/12/19.
 */
@Table(name = "auth_authority")
@Entity
public class Authority implements Serializable {

    private String id;
    private String username;
    private String role;
    private Role rolesByRole;
    private User usersByUsername;

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

        Authority that = (Authority) o;

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
    public Role getRolesByRole() {
        return rolesByRole;
    }

    public void setRolesByRole(Role rolesByRole) {
        this.rolesByRole = rolesByRole;
    }

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    public User getUsersByUsername() {
        return usersByUsername;
    }

    public void setUsersByUsername(User usersByUsername) {
        this.usersByUsername = usersByUsername;
    }
}
