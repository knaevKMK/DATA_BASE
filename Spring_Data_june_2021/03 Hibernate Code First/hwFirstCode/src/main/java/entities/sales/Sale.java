package entities.sales;

import entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    private Product product;
    private Customer customer;
    private StoreLocation storeLocation;
    private LocalDate date;

    public Sale() {
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    public Product getProduct() {
        return product;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer getCustomer() {
        return customer;
    }

    @ManyToOne
    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    @Column(columnDefinition = "DATE")
    public LocalDate getDate() {
        return date;
    }

    public Sale setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Sale setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Sale setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
        return this;
    }

    public Sale setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
