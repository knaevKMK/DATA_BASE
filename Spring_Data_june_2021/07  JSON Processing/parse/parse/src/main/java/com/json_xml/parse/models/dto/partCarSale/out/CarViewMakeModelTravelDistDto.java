package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

public class CarViewMakeModelTravelDistDto {
    @Expose
    private String Make;
    @Expose
    private String Model;
    @Expose
    private Long TravelledDistance;

    public CarViewMakeModelTravelDistDto() {
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
