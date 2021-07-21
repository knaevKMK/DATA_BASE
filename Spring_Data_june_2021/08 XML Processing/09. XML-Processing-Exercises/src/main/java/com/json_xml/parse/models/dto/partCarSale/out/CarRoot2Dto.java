package com.json_xml.parse.models.dto.partCarSale.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRoot2Dto {
    @XmlElement(name = "car")
    private Set<CarViewMakeModelTravelDistPartsListDto> carDtos;

    public CarRoot2Dto() {
    }

    public Set<CarViewMakeModelTravelDistPartsListDto> getCarDtos() {
        return carDtos;
    }

    public CarRoot2Dto setCarDtos(Set<CarViewMakeModelTravelDistPartsListDto> carDtos) {
        this.carDtos = carDtos;
        return this;
    }
}
