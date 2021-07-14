package com.json.json.models.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private int age;
    private String firstName;
    private String lastName;
    private Set<UserEntity> friends;


    public UserEntity() {
        this.friends = new HashSet<>();
    }

    @ManyToMany
    public Set<UserEntity> getFriends() {
        return friends;
    }

    public UserEntity setFriends(Set<UserEntity> friends) {
        this.friends = friends;
        return this;
    }

    @Column
    public int getAge() {
        return age;
    }

    public UserEntity setAge(int age) {
        this.age = age;
        return this;
    }

    @Column
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String first_name) {
        this.firstName = first_name;
        return this;
    }

    @Column
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String last_name) {
        this.lastName = last_name;
        return this;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "age=" + age +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", friends=" + friends +
                '}';
    }
}
