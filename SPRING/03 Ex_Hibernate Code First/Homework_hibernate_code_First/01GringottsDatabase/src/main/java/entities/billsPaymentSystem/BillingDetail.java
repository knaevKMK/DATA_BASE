package entities.billsPaymentSystem;

import entities.BaseEntite;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail extends BaseEntite {
    private User owner;

    public BillingDetail() {
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
