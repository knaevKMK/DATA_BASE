package com.json_xml.parse.models.dto.partCarSale.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesRootOutDto {

    @XmlElement(name = "sale")
    private Set<SalesDsicountDto> saleDtos;

    public SalesRootOutDto() {
    }

    public Set<SalesDsicountDto> getSaleDtos() {
        return saleDtos;
    }

    public SalesRootOutDto setSaleDtos(Set<SalesDsicountDto> saleDtos) {
        this.saleDtos = saleDtos;
        return this;
    }
}
