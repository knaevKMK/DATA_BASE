package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersIdNamePartCountDTO {
    @XmlAttribute(name = "id")
    private long id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "parts-count")
    private int partsCount;

    public SuppliersIdNamePartCountDTO() {
    }

    public long getId() {
        return id;
    }

    public SuppliersIdNamePartCountDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SuppliersIdNamePartCountDTO setName(String name) {
        this.name = name;
        return this;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public SuppliersIdNamePartCountDTO setPartsCount(int partsCount) {
        this.partsCount = partsCount;
        return this;
    }
}
