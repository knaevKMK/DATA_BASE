package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewMakeModelTravelDistDto {
    @XmlAttribute(name = "id")
    private long id;
    @XmlAttribute(name = "make")
    private String Make;
    @XmlAttribute(name = "model")
    private String Model;
    @XmlAttribute(name = "travelled-distance")
    private Long TravelledDistance;

    public CarViewMakeModelTravelDistDto() {
    }

    public long getId() {
        return id;
    }

    public CarViewMakeModelTravelDistDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getMake() {
        return Make;
    }

    public CarViewMakeModelTravelDistDto setMake(String make) {
        Make = make;
        return this;
    }

    public String getModel() {
        return Model;
    }

    public CarViewMakeModelTravelDistDto setModel(String model) {
        Model = model;
        return this;
    }

    public Long getTravelledDistance() {
        return TravelledDistance;
    }

    public CarViewMakeModelTravelDistDto setTravelledDistance(Long travelledDistance) {
        TravelledDistance = travelledDistance;
        return this;
    }
}
