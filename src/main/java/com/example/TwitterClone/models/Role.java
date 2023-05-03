package com.example.TwitterClone.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    private String authority;

    public Role(Integer roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    public Role() { }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", authority='" + authority + '\'' +
                '}';
    }
}
