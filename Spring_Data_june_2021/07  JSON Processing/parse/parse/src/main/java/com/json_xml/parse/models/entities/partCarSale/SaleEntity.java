package com.json_xml.parse.models.entities.partCarSale;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "car_id")
    public CarEntity getCar() {
        return car;
    }

    public SaleEntity setCar(CarEntity carEntity) {
        this.car = carEntity;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public CustomerEntity getCustomer() {
        return customer;
    }

    public SaleEntity setCustomer(CustomerEntity customerEntity) {
        this.customer = customerEntity;
        return this;
    }

}
