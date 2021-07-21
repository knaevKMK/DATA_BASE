package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class CarViewCarPartsDto {
    @Expose
    private CarViewMakeModelTravelDistDto car;
    @Expose
    private Set<PartViewNamePriceDTO> parts;

    public CarViewCarPartsDto() {
    }

    public CarViewMakeModelTravelDistDto getCar() {
        return car;
    }

    public CarViewCarPartsDto setCar(CarViewMakeModelTravelDistDto car) {
        this.car = car;
        return this;
    }

    public Set<PartViewNamePriceDTO> getParts() {
        return parts;
    }

    public CarViewCarPartsDto setParts(Set<PartViewNamePriceDTO> parts) {
        this.parts = parts;
        return this;
    }
}
