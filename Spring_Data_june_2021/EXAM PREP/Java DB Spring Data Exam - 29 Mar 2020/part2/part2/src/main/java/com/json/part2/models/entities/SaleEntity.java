package com.json.part2.models.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "sales")
public class SaleEntity extends BaseEntity {
    private BigDecimal discount;
    private CarEntity car;
    private CustomerEntity customer;

    public SaleEntity() {
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public SaleEntity setDiscount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    @OneToOne
    public CarEntity getCar() {
        return car;
    }

    public SaleEntity setCar(CarEntity car) {
        this.car = car;
        return this;
    }

    @OneToOne
    public CustomerEntity getCustomer() {
        return customer;
    }

    public SaleEntity setCustomer(CustomerEntity customer) {
        this.customer = customer;
        return this;
    }
}
