package com.json_xml.parse.models.dto.partCarSale.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRoot2Dto {
    @XmlElement (name = "customer")
    private Set<CustomerFullNameBoughtCarsSpentMoneyDto> custmoerDtos;

    public CustomerRoot2Dto() {
    }

    public Set<CustomerFullNameBoughtCarsSpentMoneyDto> getCustmoerDtos() {
        return custmoerDtos;
    }

    public CustomerRoot2Dto setCustmoerDtos(Set<CustomerFullNameBoughtCarsSpentMoneyDto> custmoerDtos) {
        this.custmoerDtos = custmoerDtos;
        return this;
    }
}
