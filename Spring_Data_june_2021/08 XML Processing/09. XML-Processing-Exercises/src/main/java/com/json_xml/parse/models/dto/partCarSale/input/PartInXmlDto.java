package com.json_xml.parse.models.dto.partCarSale.input;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartInXmlDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "price")
    private BigDecimal price;
    @XmlAttribute(name = "quantity")
    private Integer quantity;

    public PartInXmlDto() {
    }

    @NotNull
    @NotBlank
    public String getName() {
        return name;
    }

    public PartInXmlDto setName(String name) {
        this.name = name;
        return this;
    }

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public PartInXmlDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Positive
    public Integer getQuantity() {
        return quantity;
    }

    public PartInXmlDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
