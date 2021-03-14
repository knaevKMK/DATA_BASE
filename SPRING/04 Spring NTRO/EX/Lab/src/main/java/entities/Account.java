package entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Data

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account extends BaseEntitie {
    private BigDecimal balance;
    @ToString.Exclude
    private User user;

    public Account() {
    }

    @ManyToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

