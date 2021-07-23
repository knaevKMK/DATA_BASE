package hiberspring.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "branches")
public class BranchEntity extends BaseEntity {
    private String name;
    private Town town;
    private Set<ProductEntity> products;
    private Set<EmployeeEntity> employees;

    public BranchEntity() {
    }

    @OneToMany(mappedBy = "branch")
    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }

    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "town_id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
