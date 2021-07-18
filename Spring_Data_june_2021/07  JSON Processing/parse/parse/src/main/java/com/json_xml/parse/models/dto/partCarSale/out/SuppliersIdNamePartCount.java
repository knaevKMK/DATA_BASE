package com.json_xml.parse.models.dto.partCarSale.out;

import com.google.gson.annotations.Expose;

public class SuppliersIdNamePartCount {
    @Expose
    private long Id;
    @Expose
    private String Name;
    @Expose
    private int partsCount;

    public SuppliersIdNamePartCount() {
    }

    public long getId() {
        return Id;
    }

    public SuppliersIdNamePartCount setId(long id) {
        this.Id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public SuppliersIdNamePartCount setName(String name) {
        Name = name;
        return this;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public SuppliersIdNamePartCount setPartsCount(int partsCount) {
        this.partsCount = partsCount;
        return this;
    }
}
