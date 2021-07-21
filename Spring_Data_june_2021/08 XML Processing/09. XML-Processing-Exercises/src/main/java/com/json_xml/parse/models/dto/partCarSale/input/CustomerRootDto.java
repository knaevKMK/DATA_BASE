package com.json_xml.parse.models.dto.partCarSale.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootDto {

    @XmlElement(name = "customer")
    private Set<CustomerXmlDto> customers;


    public CustomerRootDto() {
    }

    public Set<CustomerXmlDto> getCustomers() {
        return customers;
    }

    public CustomerRootDto setCustomers(Set<CustomerXmlDto> customers) {
        this.customers = customers;
        return this;
    }
}
