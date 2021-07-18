package com.json_xml.parse.models.dto.partUserProductCategoriy.input;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInFromFileJsonDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;

    public UserInFromFileJsonDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserInFromFileJsonDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull(message = "Last name can not be null")
    @Size(min = 3, message = "Last name must be over 3 symbols")
    public String getLastName() {
        return lastName;
    }

    public UserInFromFileJsonDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserInFromFileJsonDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "UserInFromFileJsonDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
