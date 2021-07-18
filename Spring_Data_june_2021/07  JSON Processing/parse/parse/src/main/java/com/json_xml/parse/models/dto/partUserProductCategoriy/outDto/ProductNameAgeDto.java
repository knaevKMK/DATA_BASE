package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductNameAgeDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductNameAgeDto() {
    }

    public String getName() {
        return name;
    }

    public ProductNameAgeDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductNameAgeDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
