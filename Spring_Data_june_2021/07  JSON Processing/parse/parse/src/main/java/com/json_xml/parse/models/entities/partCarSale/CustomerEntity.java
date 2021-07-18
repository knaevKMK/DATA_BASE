package com.json_xml.parse.models.entities.partCarSale;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class CustomerEntity extends BaseEntity {

    private String name;
    private LocalDateTime birthDate;
    private boolean isYoungDriver;

    public CustomerEntity() {
    }

    public String getName() {
        return name;
    }

    public CustomerEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public CustomerEntity setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    @Column(name = "is_young_driver")
    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public CustomerEntity setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
        return this;
    }
}
