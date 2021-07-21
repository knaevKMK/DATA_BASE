package com.json_xml.parse.models.dto.partCarSale.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierRootOutDto {

    @XmlElement(name = "supplier")
    private Set<SuppliersIdNamePartCountDTO> suppliersDtos;

    public SupplierRootOutDto() {
    }

    public Set<SuppliersIdNamePartCountDTO> getSuppliersDtos() {
        return suppliersDtos;
    }

    public SupplierRootOutDto setSuppliersDtos(Set<SuppliersIdNamePartCountDTO> suppliersDtos) {
        this.suppliersDtos = suppliersDtos;
        return this;
    }
}
