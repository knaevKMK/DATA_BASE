package entities.sales;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Base64;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation extends BaseEntity {
    private String locationName;
    private Set<Sale> sales;

    public StoreLocation() {
    }

    @Column(name = "location_name")
    public String getLocationName() {
        return locationName;
    }

    @OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class)
    public Set<Sale> getSales() {
        return sales;
    }

    public StoreLocation setLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    public StoreLocation setSales(Set<Sale> sales) {
        this.sales = sales;
        return this;
    }
}
