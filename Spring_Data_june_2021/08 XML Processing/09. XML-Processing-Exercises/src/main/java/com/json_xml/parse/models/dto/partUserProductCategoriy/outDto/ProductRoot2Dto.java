package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.*;
import java.util.Set;


    @XmlRootElement(name = "sold-products")
    @XmlAccessorType(XmlAccessType.FIELD)
    public class ProductRoot2Dto {
        @XmlAttribute(name = "count")
        private int count;
        @XmlElement(name = "product")
        private Set<ProductVieNamePricewDto> productViewWithBuyersDtoSet;

        public ProductRoot2Dto() {
        }

        public int getCount() {
            return count;
        }

        public ProductRoot2Dto setCount(int count) {
            this.count = count;
            return this;
        }

        public Set<ProductVieNamePricewDto> getProductViewWithBuyersDtoSet() {
            return productViewWithBuyersDtoSet;
        }

        public ProductRoot2Dto setProductViewWithBuyersDtoSet(
                Set<ProductVieNamePricewDto> productViewWithBuyersDtoSet) {
            this.productViewWithBuyersDtoSet = productViewWithBuyersDtoSet;
            return this;
        }
}
