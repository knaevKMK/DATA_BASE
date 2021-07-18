package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;
import com.json_xml.parse.models.entities.partCarSale.SaleEntity;

import java.time.LocalDateTime;
import java.util.Set;

public class CustomersOrderByBirthDateDescDTO {
    @Expose
    private long Id;
    @Expose
    private String Name;
    @Expose
    private String BirthDate;
    @Expose
    private boolean IsYoungDriver;
    @Expose
    private Set<SaleEntity> Sales;

    public CustomersOrderByBirthDateDescDTO() {
    }

    public long getId() {
        return Id;
    }

    public CustomersOrderByBirthDateDescDTO setId(long id) {
        this.Id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public CustomersOrderByBirthDateDescDTO setName(String name) {
        this.Name = name;
        return this;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public CustomersOrderByBirthDateDescDTO setBirthDate(String birthDate) {
        this.BirthDate = birthDate;
        return this;
    }

    public boolean isYoungDriver() {
        return IsYoungDriver;
    }

    public CustomersOrderByBirthDateDescDTO setYoungDriver(boolean youngDriver) {
        IsYoungDriver = youngDriver;
        return this;
    }

    public Set<SaleEntity> getSales() {
        return Sales;
    }

    public CustomersOrderByBirthDateDescDTO setSales(Set<SaleEntity> sales) {
        Sales = sales;
        return this;
    }
}
