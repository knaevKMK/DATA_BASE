package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

public class SuppliersIdNamePartCountDTO {
    @Expose
    private long Id;
    @Expose
    private String Name;
    @Expose
    private int partsCount;

    public SuppliersIdNamePartCountDTO() {
    }

    public long getId() {
        return Id;
    }

    public SuppliersIdNamePartCountDTO setId(long id) {
        this.Id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public SuppliersIdNamePartCountDTO setName(String name) {
        Name = name;
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
