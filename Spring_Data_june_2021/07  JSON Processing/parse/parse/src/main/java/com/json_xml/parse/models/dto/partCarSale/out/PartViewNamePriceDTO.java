package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartViewNamePriceDTO {
    @Expose
    private String Name;
    @Expose
    private BigDecimal Price;

    public PartViewNamePriceDTO() {
    }

    public String getName() {
        return Name;
    }

    public PartViewNamePriceDTO setName(String name) {
        Name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public PartViewNamePriceDTO setPrice(BigDecimal price) {
        Price = price;
        return this;
    }
}
