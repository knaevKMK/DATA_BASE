package com.json.part2.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "parts")
public class PartEntity extends BaseEntity {
    private String name;
    private BigDecimal price;
    private int quantity;
    private SupplierEntity supplier;
    private List<CarEntity> cars;
    public PartEntity() {
    }

    public String getName() {
        return name;
    }

    public PartEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PartEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public PartEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @ManyToOne
    public SupplierEntity getSupplier() {
        return supplier;
    }

    public PartEntity setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
        return this;
    }

    @ManyToMany
    public List<CarEntity> getCars() {
        return cars;
    }

    public PartEntity setCars(List<CarEntity> cars) {
        this.cars = cars;
        return this;
    }
}
