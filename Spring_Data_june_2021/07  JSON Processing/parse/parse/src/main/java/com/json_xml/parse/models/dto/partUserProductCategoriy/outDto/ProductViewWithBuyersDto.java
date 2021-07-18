package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductViewWithBuyersDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String buyerFirstName;
    @Expose
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
