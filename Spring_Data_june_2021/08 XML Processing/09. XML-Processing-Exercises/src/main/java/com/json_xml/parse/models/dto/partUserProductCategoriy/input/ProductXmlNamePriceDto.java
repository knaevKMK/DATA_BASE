package com.json_xml.parse.models.dto.partUserProductCategoriy.input;


import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductXmlNamePriceDto {
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "price")
    private BigDecimal price;

    public ProductXmlNamePriceDto() {
    }

    @Size(min = 3, message = "name must be min 3 symbols")
    public String getName() {
        return name;
    }

    public ProductXmlNamePriceDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductXmlNamePriceDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
