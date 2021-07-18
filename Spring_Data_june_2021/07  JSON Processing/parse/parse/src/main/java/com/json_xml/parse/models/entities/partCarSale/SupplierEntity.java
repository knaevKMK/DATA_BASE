package com.json_xml.parse.models.entities.partCarSale;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class SupplierEntity extends BaseEntity {
    private String name;
    private boolean isImporter;
    private Set<PartEntity> parts;

    public SupplierEntity() {
    }

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    public Set<PartEntity> getParts() {
        return parts;
    }

    public SupplierEntity setParts(Set<PartEntity> parts) {
        this.parts = parts;
        return this;
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
