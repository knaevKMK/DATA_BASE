package com.json_xml.parse.models.dto.partCarSale.input;

import com.google.gson.annotations.Expose;

public class CarInputJsonDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Long travelledDistance;

    public CarInputJsonDto() {
    }

    public String getMake() {
        return make;
    }

    public CarInputJsonDto setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CarInputJsonDto setModel(String model) {
        this.model = model;
        return this;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public CarInputJsonDto setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
        return this;
    }
}
