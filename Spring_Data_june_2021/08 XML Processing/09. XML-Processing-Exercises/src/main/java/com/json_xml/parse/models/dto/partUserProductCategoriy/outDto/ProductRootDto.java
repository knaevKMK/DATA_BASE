package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootDto {
    @XmlElement(name = "product")
    private Set<ProductViewWithBuyersDto> productViewWithBuyersDtoSet;

    public ProductRootDto() {
    }

    public Set<ProductViewWithBuyersDto> getProductViewWithBuyersDtoSet() {
        return productViewWithBuyersDtoSet;
    }

    public ProductRootDto setProductViewWithBuyersDtoSet(Set<ProductViewWithBuyersDto> productViewWithBuyersDtoSet) {
        this.productViewWithBuyersDtoSet = productViewWithBuyersDtoSet;
        return this;
    }
}
