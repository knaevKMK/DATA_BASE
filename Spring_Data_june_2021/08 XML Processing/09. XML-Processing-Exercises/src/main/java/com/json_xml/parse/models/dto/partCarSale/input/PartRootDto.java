package com.json_xml.parse.models.dto.partCarSale.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name="parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootDto {
    @XmlElement(name="part")
    private Set<PartInXmlDto> parts;

    public PartRootDto() {
    }

    public Set<PartInXmlDto> getParts() {
        return parts;
    }

    public PartRootDto setParts(Set<PartInXmlDto> parts) {
        this.parts = parts;
        return this;
    }
}
