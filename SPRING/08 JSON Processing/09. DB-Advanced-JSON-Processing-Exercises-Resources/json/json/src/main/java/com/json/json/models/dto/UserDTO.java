package com.json.json.models.dto;

import com.google.gson.annotations.Expose;
import com.json.json.models.entites.UserEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    @Expose
    private int age;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    private Set<UserEntity> friends;

    public UserDTO() {
        this.friends = new HashSet<>();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "age=" + getAge() +
                ", first_name='" + getFirstName() + '\'' +
                ", last_name='" + getLastName() + '\'' +
                ", friends=" +
                getFriends().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(System.lineSeparator())) +
                '}';
    }

    public int getAge() {
        return age;
    }

    public UserDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String first_name) {
        this.firstName = first_name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String last_name) {
        this.lastName = last_name;
        return this;
    }

    public Set<UserEntity> getFriends() {
        return friends;
    }

    public UserDTO setFriends(Set<UserEntity> friends) {
        this.friends = friends;
        return this;
    }
}
