package com.json_xml.parse.models.entities.partCarSale;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "sales")
public class SaleEntity extends BaseEntity {
    private BigDecimal discount;
    private CarEntity carEntity;
    private CustomerEntity customerEntity;

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
        return carEntity;
    }

    public SaleEntity setCar(CarEntity carEntity) {
        this.carEntity = carEntity;
        return this;
    }

    @OneToOne
    public CustomerEntity getCustomer() {
        return customerEntity;
    }

    public SaleEntity setCustomer(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
        return this;
    }
}
