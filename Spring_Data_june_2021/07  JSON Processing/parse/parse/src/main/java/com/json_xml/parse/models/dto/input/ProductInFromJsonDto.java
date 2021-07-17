package com.json_xml.parse.models.dto.input;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class ProductInFromJsonDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductInFromJsonDto() {
    }

    @Length(min = 3, message = "name must be min 3 symbols")
    public String getName() {
        return name;
    }

    public ProductInFromJsonDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductInFromJsonDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
