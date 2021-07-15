package com.json.part2.models.importDto;

import com.google.gson.annotations.Expose;

public class SupplierSeedDTO {
    @Expose
    private String name;
    @Expose
    private boolean isImporter;

    public SupplierSeedDTO() {
    }

    public String getName() {
        return name;
    }

    public SupplierSeedDTO setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public SupplierSeedDTO setIsImporter(boolean promoted) {
        isImporter = promoted;
        return this;
    }
}
