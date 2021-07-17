package com.gameHall.user.models.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String email;
    private String password;
    private String fullName;
    private UserRole role;
    private Set<OrderEntity> oreders;


    public UserEntity() {
    }

    @OneToMany(mappedBy = "buyer",fetch = FetchType.EAGER)
    public Set<OrderEntity> getOreders() {
        return oreders;
    }

    public UserEntity setOreders(Set<OrderEntity> oreders) {
        this.oreders = oreders;
        return this;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column
    public String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }


    @Column
    public UserRole getRole() {
        return role;
    }

    public UserEntity setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
