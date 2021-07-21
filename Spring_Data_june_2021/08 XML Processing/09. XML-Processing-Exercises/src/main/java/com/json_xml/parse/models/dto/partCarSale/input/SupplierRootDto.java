package com.json_xml.parse.models.dto.partCarSale.input;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierRootDto {

    @XmlElement(name = "supplier")
    private Set<SupplierXmlDto>supplierDtos;

    public SupplierRootDto() {
    }

    public Set<SupplierXmlDto> getSupplierDtos() {
        return supplierDtos;
    }

    public SupplierRootDto setSupplierDtos(Set<SupplierXmlDto> supplierDtos) {
        this.supplierDtos = supplierDtos;
        return this;
    }
}
