package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartViewNamePriceDTO {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "price")
    private BigDecimal price;

    public PartViewNamePriceDTO() {
    }

    public String getName() {
        return name;
    }

    public PartViewNamePriceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PartViewNamePriceDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
