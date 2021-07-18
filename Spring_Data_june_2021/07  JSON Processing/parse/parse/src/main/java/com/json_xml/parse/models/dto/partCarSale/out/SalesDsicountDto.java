package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SalesDsicountDto {
    @Expose
    private CarViewMakeModelTravelDistDto car;
    @Expose
    private String customerName;
    @Expose
    private BigDecimal Discount;
    @Expose
    private BigDecimal price;
    @Expose
    private BigDecimal priceWithDiscount;

    public SalesDsicountDto() {
    }

    public CarViewMakeModelTravelDistDto getCar() {
        return car;
    }

    public SalesDsicountDto setCar(CarViewMakeModelTravelDistDto car) {
        this.car = car;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public SalesDsicountDto setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public BigDecimal getDiscount() {
        return Discount;
    }

    public SalesDsicountDto setDiscount(BigDecimal discount) {
        Discount = discount;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SalesDsicountDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public SalesDsicountDto setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
        return this;
    }
}
