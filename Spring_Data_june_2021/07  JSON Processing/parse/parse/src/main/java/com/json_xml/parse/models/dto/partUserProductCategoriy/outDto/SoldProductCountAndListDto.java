package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class SoldProductCountAndListDto {
    @Expose
    private int count;
    @Expose
    private Set<ProductNameAgeDto> products;

    public SoldProductCountAndListDto() {
    }

    public int getCount() {
        return count;
    }

    public SoldProductCountAndListDto setCount(int count) {
        this.count = count;
        return this;
    }

    public Set<ProductNameAgeDto> getProducts() {
        return products;
    }

    public SoldProductCountAndListDto setProducts(Set<ProductNameAgeDto> products) {
        this.products = products;
        return this;
    }
}
