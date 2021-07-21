package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

public class CarByMakeSortByModelAndTravelDistDto {
    @Expose
    private long id;
    @Expose
    private String Make;
    @Expose
    private String Model;
    @Expose
    private long TravelledDistance;

    public CarByMakeSortByModelAndTravelDistDto() {
    }

    public long getId() {
        return id;
    }

    public CarByMakeSortByModelAndTravelDistDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getMake() {
        return Make;
    }

    public CarByMakeSortByModelAndTravelDistDto setMake(String make) {
        Make = make;
        return this;
    }

    public String getModel() {
        return Model;
    }

    public CarByMakeSortByModelAndTravelDistDto setModel(String model) {
        Model = model;
        return this;
    }

    public long getTravelledDistance() {
        return TravelledDistance;
    }

    public CarByMakeSortByModelAndTravelDistDto setTravelledDistance(long travelledDistance) {
        TravelledDistance = travelledDistance;
        return this;
    }
}
