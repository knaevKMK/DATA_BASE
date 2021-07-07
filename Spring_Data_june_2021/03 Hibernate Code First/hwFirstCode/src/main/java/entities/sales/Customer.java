package entities.sales;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    private String name;
    private String email;
    private String creditCardNumber;
    private Set<Sale> sales;

    public Customer() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "credit_card_number")
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    @OneToMany(mappedBy = "customer",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Sale> getSales() {
        return sales;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public Customer setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public Customer setSales(Set<Sale> sales) {
        this.sales = sales;
        return this;
    }
}
