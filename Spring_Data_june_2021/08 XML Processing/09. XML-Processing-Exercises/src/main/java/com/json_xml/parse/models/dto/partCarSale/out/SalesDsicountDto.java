package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesDsicountDto {
    @XmlElement(name="car")
    private CarViewMakeModelTravelDistDto car;
    @XmlElement(name = "customer-name")
    private String customerName;
    @XmlElement(name = "distance")
    private BigDecimal discount;
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "price-with-discount")
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
        return discount;
    }

    public SalesDsicountDto setDiscount(BigDecimal discount) {
        this.discount = discount;
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
