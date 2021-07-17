package com.json_xml.parse.models.dto.outDto;

import com.google.gson.annotations.Expose;

public class UserFullNameToJsonDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;

    public UserFullNameToJsonDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserFullNameToJsonDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserFullNameToJsonDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
