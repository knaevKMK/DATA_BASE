package com.json_xml.parse.models.entities.partCarSale;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "parts")
public class PartEntity extends BaseEntity {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private SupplierEntity supplier;
    private Set<CarEntity> cars;

    public PartEntity() {
    }

    @ManyToMany(mappedBy = "parts", fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    public Set<CarEntity> getCars() {
        return cars;
    }

    public PartEntity setCars(Set<CarEntity> carEntities) {
        this.cars = carEntities;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    public SupplierEntity getSupplier() {
        return supplier;
    }

    public PartEntity setSupplier(SupplierEntity supplierEntity) {
        this.supplier = supplierEntity;
        return this;
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

    public Integer getQuantity() {
        return quantity;
    }

    public PartEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartEntity that = (PartEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
