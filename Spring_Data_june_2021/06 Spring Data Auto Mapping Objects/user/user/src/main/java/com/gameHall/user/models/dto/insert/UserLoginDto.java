package com.gameHall.user.models.dto.insert;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserLoginDto {
    private String email;
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Email(regexp = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "Incorrect email")
    public String getEmail() {
        return email;
    }

    public UserLoginDto setEmail(String email) {
        this.email = email;
        return this;
    }

    @Pattern(regexp = "[A-Za-z\\d]{6,}", message = "Incorrect password")

    public String getPassword() {
        return password;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
