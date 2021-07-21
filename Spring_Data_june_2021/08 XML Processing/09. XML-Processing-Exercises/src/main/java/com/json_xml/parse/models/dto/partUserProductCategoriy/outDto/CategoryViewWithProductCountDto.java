package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryViewWithProductCountDto {
    @XmlAttribute
    private String name;
    @XmlElement(name = "products-count")
    private int productsCount;
    @XmlElement(name = "average-price")
    private BigDecimal averagePrice;
    @XmlElement(name = "total-revenue")
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
