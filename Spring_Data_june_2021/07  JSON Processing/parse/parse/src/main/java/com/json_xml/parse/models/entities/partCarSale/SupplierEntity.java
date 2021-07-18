package com.json_xml.parse.models.entities.partCarSale;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "suppliers")
public class SupplierEntity extends BaseEntity {
    private String name;
    private boolean isImporter;

    public SupplierEntity() {
    }

    public String getName() {
        return name;
    }

    public SupplierEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "is_importer")
    public boolean isImporter() {
        return isImporter;
    }

    public SupplierEntity setImporter(boolean importer) {
        isImporter = importer;
        return this;
    }
}
