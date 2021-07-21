package com.json_xml.parse.models.dto.partCarSale.out;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerFullNameBoughtCarsSpentMoneyDto {

    @XmlAttribute(name = "full-name")
    private String fullName;
    @XmlAttribute(name = "bought-cars")
    private int countCars;
    @XmlAttribute(name = "spent-money")
    private BigDecimal spentMoney;

    public CustomerFullNameBoughtCarsSpentMoneyDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public CustomerFullNameBoughtCarsSpentMoneyDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public int getCountCars() {
        return countCars;
    }

    public CustomerFullNameBoughtCarsSpentMoneyDto setCountCars(int countCars) {
        this.countCars = countCars;
        return this;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public CustomerFullNameBoughtCarsSpentMoneyDto setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
        return this;
    }
}
