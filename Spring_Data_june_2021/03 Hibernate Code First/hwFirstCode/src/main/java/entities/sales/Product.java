package entities.sales;

import entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {


    private String name;
    private Double quantity;
    private BigDecimal price;
    private Set<Sale> sales;

    public Product() {
    }

    @Column
    public String getName() {
        return name;
    }

    @Column(name="quantity", columnDefinition = "DOUBLE")
    public Double getQuantity() {
        return quantity;
    }

    @Column (name="price",columnDefinition = "DECIMAL")
    public BigDecimal getPrice() {
        return price;
    }


    @OneToMany(mappedBy = "product",cascade = CascadeType.PERSIST)
    public Set<Sale> getSales() {
        return sales;
    }

    //all setters
    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Product setSales(Set<Sale> sales) {
        this.sales = sales;
        return this;
    }


}
