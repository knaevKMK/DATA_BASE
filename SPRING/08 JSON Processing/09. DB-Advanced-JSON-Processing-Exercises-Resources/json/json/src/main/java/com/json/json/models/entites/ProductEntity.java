package com.json.json.models.entites;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    private String name;
    private BigDecimal price;
    private Set<UserEntity> buyers;
    private Set<UserEntity> sellers;
    private Set<CategoryEntity> categories;

    public ProductEntity() {
    }

    @OneToMany
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public Set<UserEntity> getBuyers() {
        return buyers;
    }

    public ProductEntity setBuyers(Set<UserEntity> buyers) {
        this.buyers = buyers;
        return this;
    }

    @OneToMany
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    public Set<UserEntity> getSellers() {
        return sellers;
    }

    public ProductEntity setSellers(Set<UserEntity> sellers) {
        this.sellers = sellers;
        return this;
    }

    @ManyToMany(mappedBy = "products")
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public ProductEntity setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


}
