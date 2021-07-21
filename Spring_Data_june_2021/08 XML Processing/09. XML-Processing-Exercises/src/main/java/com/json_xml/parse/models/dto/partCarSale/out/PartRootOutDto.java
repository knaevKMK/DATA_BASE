package com.json_xml.parse.models.dto.partCarSale.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name="parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootOutDto {

    @XmlElement(name="part")
    private Set<PartViewNamePriceDTO> partDtos;

    public PartRootOutDto() {
    }

    public Set<PartViewNamePriceDTO> getPartDtos() {
        return partDtos;
    }

    public PartRootOutDto setPartDtos(Set<PartViewNamePriceDTO> partDtos) {
        this.partDtos = partDtos;
        return this;
    }
}
