package com.json_xml.parse.models.dto.outDto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductViewJsonDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String _seller;

    public ProductViewJsonDto() {
    }

    public String getName() {
        return name;
    }

    public ProductViewJsonDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewJsonDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String get_seller() {
        return _seller;
    }

    public ProductViewJsonDto set_seller(String _seller) {
        this._seller = _seller;
        return this;
    }
}
