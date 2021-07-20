package com.json_xml.parse.models.dto.partUserProductCategoriy.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootXmlDto {

    @XmlElement(name = "category")
    private Set<CategoryXmlNameDto> categoryXmlNameDtos;

    public CategoryRootXmlDto() {
    }

    public Set<CategoryXmlNameDto> getCategoryXmlNameDtos() {
        return categoryXmlNameDtos;
    }

    public CategoryRootXmlDto setCategoryXmlNameDtos(Set<CategoryXmlNameDto> categoryXmlNameDtos) {
        this.categoryXmlNameDtos = categoryXmlNameDtos;
        return this;
    }
}
