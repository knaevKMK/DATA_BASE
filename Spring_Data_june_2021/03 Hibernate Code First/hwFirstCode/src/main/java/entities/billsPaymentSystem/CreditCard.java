package entities.billsPaymentSystem;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail {
    private String type;
    private String expiritionMoth;
    private String expiritionYear;

    public CreditCard() {
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "expirity_month")
    public String getExpiritionMoth() {
        return expiritionMoth;
    }

    public void setExpiritionMoth(String expiritionMoth) {
        this.expiritionMoth = expiritionMoth;
    }

    @Column(name = "expirity_year")
    public String getExpiritionYear() {
        return expiritionYear;
    }

    public void setExpiritionYear(String expiritionYear) {
        this.expiritionYear = expiritionYear;
    }
}
