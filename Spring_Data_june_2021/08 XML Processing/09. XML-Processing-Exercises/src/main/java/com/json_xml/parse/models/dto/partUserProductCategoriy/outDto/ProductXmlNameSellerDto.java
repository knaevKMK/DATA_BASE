package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductXmlNameSellerDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "price")
    private BigDecimal price;
    @XmlAttribute(name = "seller")
    private String seller;

    public ProductXmlNameSellerDto() {
    }

    public String getSeller() {
        return seller;
    }

    public ProductXmlNameSellerDto setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductXmlNameSellerDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductXmlNameSellerDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
