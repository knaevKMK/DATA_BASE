package com.json_xml.parse.models.dto.partCarSale.input;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierXmlDto {
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SupplierXmlDto() {
    }

    public String getName() {
        return name;
    }

    public SupplierXmlDto setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public SupplierXmlDto setImporter(boolean importer) {
        isImporter = importer;
        return this;
    }
}
