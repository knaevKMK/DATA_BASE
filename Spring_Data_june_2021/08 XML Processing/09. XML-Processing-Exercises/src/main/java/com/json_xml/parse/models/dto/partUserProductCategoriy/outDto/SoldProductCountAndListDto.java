package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class SoldProductCountAndListDto {
    @Expose
    private int count;
    @Expose
    private Set<ProductXmlNameSellerDto> products;

    public SoldProductCountAndListDto() {
    }

    public int getCount() {
        return count;
    }

    public SoldProductCountAndListDto setCount(int count) {
        this.count = count;
        return this;
    }

    public Set<ProductXmlNameSellerDto> getProducts() {
        return products;
    }

    public SoldProductCountAndListDto setProducts(Set<ProductXmlNameSellerDto> products) {
        this.products = products;
        return this;
    }
}
