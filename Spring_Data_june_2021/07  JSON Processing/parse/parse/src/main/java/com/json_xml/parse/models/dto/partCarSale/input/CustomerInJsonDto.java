package com.json_xml.parse.models.dto.partCarSale.input;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

public class CustomerInJsonDto {
    @Expose
    private String name;
    @Expose
    private LocalDateTime birthDate;
    @Expose
    private boolean isYoungDriver;

    public CustomerInJsonDto() {
    }

    public String getName() {
        return name;
    }

    public CustomerInJsonDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public CustomerInJsonDto setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public CustomerInJsonDto setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
        return this;
    }
}
