package com.json_xml.parse.models.entities.partUserProductCategoriy;

import com.json_xml.parse.models.entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    private String name;
    private BigDecimal price;
    private UserEntity buyer;
    private UserEntity seller;
    private Set<CategoryEntity> categories;


    public ProductEntity() {
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

    @ManyToOne
    public UserEntity getBuyer() {
        return buyer;
    }

    public ProductEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }
    @ManyToOne
    public UserEntity getSeller() {
        return seller;
    }

    public ProductEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    @ManyToMany
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public ProductEntity setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(buyer, that.buyer) && Objects.equals(seller, that.seller) && Objects.equals(categories, that.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, buyer, seller, categories);
    }
}
