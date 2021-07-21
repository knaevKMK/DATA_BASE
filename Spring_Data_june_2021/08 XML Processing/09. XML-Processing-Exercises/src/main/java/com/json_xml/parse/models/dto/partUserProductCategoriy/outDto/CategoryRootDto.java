package com.json_xml.parse.models.dto.partUserProductCategoriy.outDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootDto {
    @XmlElement(name = "category")
    private Set<CategoryViewWithProductCountDto> categoryDtos;

    public CategoryRootDto() {
    }

    public Set<CategoryViewWithProductCountDto> getCategoryDtos() {
        return categoryDtos;
    }

    public CategoryRootDto setCategoryDtos(Set<CategoryViewWithProductCountDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
        return this;
    }
}
