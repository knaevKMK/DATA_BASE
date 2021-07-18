package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryViewWithProductCountDto {
    @Expose
    private String name;
    @Expose
    private int productsCount;
    @Expose
    private BigDecimal averagePrice;
    @Expose
    private BigDecimal totalRevenue;

    public CategoryViewWithProductCountDto() {
    }

    public String getName() {
        return name;
    }

    public CategoryViewWithProductCountDto setName(String name) {
        this.name = name;
        return this;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public CategoryViewWithProductCountDto setProductsCount(int productsCount) {
        this.productsCount = productsCount;
        return this;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public CategoryViewWithProductCountDto setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
        return this;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public CategoryViewWithProductCountDto setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
        return this;
    }
    //                "productsCount": 49,
//                "averagePrice": 754.327755,
//                "totalRevenue": 36962.06

}
