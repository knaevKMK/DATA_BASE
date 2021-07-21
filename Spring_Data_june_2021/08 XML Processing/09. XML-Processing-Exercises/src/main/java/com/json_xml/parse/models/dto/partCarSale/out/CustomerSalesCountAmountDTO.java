package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CustomerSalesCountAmountDTO {
    @Expose
    private String fullName;
    @Expose
    private Integer boughtCars;
      @Expose
    private BigDecimal spentMoney;

    public CustomerSalesCountAmountDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public CustomerSalesCountAmountDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public CustomerSalesCountAmountDTO setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
        return this;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public CustomerSalesCountAmountDTO setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
        return this;
    }
}
