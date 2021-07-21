package com.json_xml.parse.models.dto.partCarSale.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootDto {
    @XmlElement(name = "car")
    private Set <CarViewMakeModelTravelDistDto> carDtos;

    public CarRootDto() {
    }

    public Set<CarViewMakeModelTravelDistDto> getCarDtos() {
        return carDtos;
    }

    public CarRootDto setCarDtos(Set<CarViewMakeModelTravelDistDto> carDtos) {
        this.carDtos = carDtos;
        return this;
    }
}
