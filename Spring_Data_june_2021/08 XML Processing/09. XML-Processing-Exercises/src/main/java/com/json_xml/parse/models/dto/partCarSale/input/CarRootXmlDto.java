package com.json_xml.parse.models.dto.partCarSale.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootXmlDto {

    @XmlElement(name = "car")
    private Set<CarXmlMakeModelDistanceDto> carXmlMakeModelDistanceDtos;


    public CarRootXmlDto() {
    }

    public Set<CarXmlMakeModelDistanceDto> getCarXmlMakeModelDistanceDtos() {
        return carXmlMakeModelDistanceDtos;
    }

    public CarRootXmlDto setCarXmlMakeModelDistanceDtos(Set<CarXmlMakeModelDistanceDto> carXmlMakeModelDistanceDtos) {
        this.carXmlMakeModelDistanceDtos = carXmlMakeModelDistanceDtos;
        return this;
    }
}
