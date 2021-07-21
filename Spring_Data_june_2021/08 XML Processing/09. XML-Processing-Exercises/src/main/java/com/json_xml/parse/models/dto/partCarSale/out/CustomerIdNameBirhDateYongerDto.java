package com.json_xml.parse.models.dto.partCarSale.out;

import com.json_xml.parse.config.LocalDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerIdNameBirhDateYongerDto {
    @XmlElement(name = "id")
    private long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;
    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public CustomerIdNameBirhDateYongerDto() {
    }

    public long getId() {
        return id;
    }

    public CustomerIdNameBirhDateYongerDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerIdNameBirhDateYongerDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public CustomerIdNameBirhDateYongerDto setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public CustomerIdNameBirhDateYongerDto setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
        return this;
    }
}
