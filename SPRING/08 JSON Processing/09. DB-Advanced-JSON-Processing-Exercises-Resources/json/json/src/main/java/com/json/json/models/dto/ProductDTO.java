package com.json.json.models.dto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductDTO {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }
}
