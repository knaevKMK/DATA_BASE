package com.json_xml.parse.models.dto.partUserProductCategoriy.input;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryXmlNameDto {
    @XmlElement(name = "name")
    private String name;

    public CategoryXmlNameDto() {
    }

    @Size(min = 3, max = 15, message = "Name must contains between 3 and 15 symbols")
    public String getName() {
        return name;
    }

    public CategoryXmlNameDto setName(String name) {
        this.name = name;
        return this;
    }
}
