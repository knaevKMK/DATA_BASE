package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "sold-product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductVieNamePricewDto {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private BigDecimal price;

    public ProductVieNamePricewDto() {
    }

    public String getName() {
        return name;
    }

    public ProductVieNamePricewDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductVieNamePricewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
