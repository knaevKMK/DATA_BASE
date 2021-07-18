package com.json_xml.parse.models.dto.partCarSale.input;

import com.google.gson.annotations.Expose;

public class SupplierInJsonDto {
    @Expose
    private String name;
    @Expose
    private boolean isImporter;

    public SupplierInJsonDto() {
    }

    public String getName() {
        return name;
    }

    public SupplierInJsonDto setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public SupplierInJsonDto setImporter(boolean importer) {
        isImporter = importer;
        return this;
    }
}
