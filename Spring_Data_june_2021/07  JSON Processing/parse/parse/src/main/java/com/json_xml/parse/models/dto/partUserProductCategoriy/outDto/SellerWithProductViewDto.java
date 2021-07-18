package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class SellerWithProductViewDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Set<ProductViewWithBuyersDto> soldProducts;

    public SellerWithProductViewDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public SellerWithProductViewDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SellerWithProductViewDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Set<ProductViewWithBuyersDto> getProducts() {
        return soldProducts;
    }

    public SellerWithProductViewDto setProducts(Set<ProductViewWithBuyersDto> products) {
        this.soldProducts = products;
        return this;
    }
}
