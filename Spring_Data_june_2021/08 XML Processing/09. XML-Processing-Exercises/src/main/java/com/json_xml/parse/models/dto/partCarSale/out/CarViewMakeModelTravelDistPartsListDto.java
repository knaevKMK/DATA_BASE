package com.json_xml.parse.models.dto.partCarSale.out;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarViewMakeModelTravelDistPartsListDto {

    @XmlAttribute(name = "make")
    private String Make;
    @XmlAttribute(name = "model")
    private String Model;
    @XmlAttribute(name = "travelled-distance")
    private Long TravelledDistance;
    @XmlElement(name = "parts")
    private PartRootOutDto parts;

    public CarViewMakeModelTravelDistPartsListDto() {
    }

    public String getMake() {
        return Make;
    }

    public CarViewMakeModelTravelDistPartsListDto setMake(String make) {
        Make = make;
        return this;
    }

    public String getModel() {
        return Model;
    }

    public CarViewMakeModelTravelDistPartsListDto setModel(String model) {
        Model = model;
        return this;
    }

    public Long getTravelledDistance() {
        return TravelledDistance;
    }

    public CarViewMakeModelTravelDistPartsListDto setTravelledDistance(Long travelledDistance) {
        TravelledDistance = travelledDistance;
        return this;
    }

    public PartRootOutDto getParts() {
        return parts;
    }

    public CarViewMakeModelTravelDistPartsListDto setParts(PartRootOutDto parts) {
        this.parts = parts;
        return this;
    }
}
