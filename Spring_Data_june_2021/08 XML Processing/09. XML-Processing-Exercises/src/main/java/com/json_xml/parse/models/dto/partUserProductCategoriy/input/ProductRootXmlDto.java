package com.json_xml.parse.models.dto.partUserProductCategoriy.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootXmlDto {

    @XmlElement(name = "product")
    private Set<ProductXmlNamePriceDto> productXmlNamePriceDtos;

    public ProductRootXmlDto() {
    }

    public Set<ProductXmlNamePriceDto> getProductXmlNamePriceDtos() {
        return productXmlNamePriceDtos;
    }

    public ProductRootXmlDto setProductXmlNamePriceDtos(Set<ProductXmlNamePriceDto> productXmlNamePriceDtos) {
        this.productXmlNamePriceDtos = productXmlNamePriceDtos;
        return this;
    }
}
