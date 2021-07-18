package com.json_xml.parse.models.entities.partCarSale;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {
    private String make;
    private String model;
    private Long travelledDistance;
    private Set<PartEntity> parts;


    public CarEntity() {
    }

    @ManyToMany
    @JoinTable(name = "parts_cars",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    public Set<PartEntity> getParts() {
        return parts;
    }

    public CarEntity setParts(Set<PartEntity> partEntities) {
        this.parts = partEntities;
        return this;
    }

    public String getMake() {
        return make;
    }

    public CarEntity setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public CarEntity setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
        return this;
    }
}
