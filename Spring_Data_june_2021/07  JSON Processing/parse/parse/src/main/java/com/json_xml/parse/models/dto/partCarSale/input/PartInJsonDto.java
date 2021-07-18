package com.json_xml.parse.models.dto.partCarSale.input;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartInJsonDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private Integer quantity;

    public PartInJsonDto() {
    }

    public String getName() {
        return name;
    }

    public PartInJsonDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PartInJsonDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public PartInJsonDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
