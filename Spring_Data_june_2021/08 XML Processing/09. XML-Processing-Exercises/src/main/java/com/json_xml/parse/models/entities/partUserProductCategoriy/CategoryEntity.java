package com.json_xml.parse.models.entities.partUserProductCategoriy;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    private String name;
    private Set<ProductEntity> products;

    public CategoryEntity() {
    }

    @ManyToMany(mappedBy = "categories"
            , fetch = FetchType.EAGER)


    public Set<ProductEntity> getProducts() {
        return products;
    }

    public CategoryEntity setProducts(Set<ProductEntity> products) {
        this.products = products;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }
}
