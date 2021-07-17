package com.json_xml.parse.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private int age;
    private String firstName;
    private String lastName;
    private Set<UserEntity> friends;


    public UserEntity() {
    }


    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    public Set<UserEntity> getFriends() {
        return friends;
    }

    public UserEntity setFriends(Set<UserEntity> friends) {
        this.friends = friends;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
