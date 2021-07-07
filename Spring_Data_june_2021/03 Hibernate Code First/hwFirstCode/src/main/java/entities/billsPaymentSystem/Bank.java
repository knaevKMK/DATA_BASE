package entities.billsPaymentSystem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class Bank extends BillingDetail {
    private String name;
    private String swift;

    public Bank() {
    }

    @Column(name = "bank")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "swift")
    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }
}