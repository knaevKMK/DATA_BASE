package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductOutRootDto {
    @XmlElement(name = "product")
    private Set<ProductXmlNameSellerDto> productXmlNameSellerDtoSet;

    public ProductOutRootDto() {
    }

    public Set<ProductXmlNameSellerDto> getProductXmlNameSellerDtoSet() {
        return productXmlNameSellerDtoSet;
    }

    public ProductOutRootDto setProductXmlNameSellerDtoSet(Set<ProductXmlNameSellerDto> productXmlNameSellerDtoSet) {
        this.productXmlNameSellerDtoSet = productXmlNameSellerDtoSet;
        return this;
    }
}
