package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductViewWithBuyersDto {
    @XmlElement
    private String name;
    @XmlElement
    private BigDecimal price;
    @XmlElement(name = "buyer-first-name")
    private String buyerFirstName;
    @XmlElement(name = "buyer-last-name")
    private String buyerLastName;

    public ProductViewWithBuyersDto() {
    }

    public String getName() {
        return name;
    }

    public ProductViewWithBuyersDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewWithBuyersDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public ProductViewWithBuyersDto setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
        return this;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public ProductViewWithBuyersDto setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
        return this;
    }
}
