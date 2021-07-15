package com.json.part2.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {


    private String make;
    private String model;
    private Long travelledDistance;
    private List<PartEntity> parts;

    public CarEntity() {
    }

    @ManyToMany(mappedBy = "cars")
    public List<PartEntity> getParts() {
        return parts;
    }

    public CarEntity setParts(List<PartEntity> parts) {
        this.parts = parts;
        return this;
    }

    @Column
    public String getMake() {
        return make;
    }

    public CarEntity setMake(String make) {
        this.make = make;
        return this;
    }

    @Column
    public String getModel() {
        return model;
    }

    public CarEntity setModel(String model) {
        this.model = model;
        return this;
    }

    @Column
    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public CarEntity setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
        return this;
    }
}
