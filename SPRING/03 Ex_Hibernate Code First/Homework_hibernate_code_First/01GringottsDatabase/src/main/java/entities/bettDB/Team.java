package entities.bettDB;

import entities.BaseEntite;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntite {
    private String name;
    private String logo;
    private String initials;
    private Set<Color> colors;

    private Town town;
    private BigDecimal budget;

    public Team() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Column(name = "initials", length = 3)
    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }


    @OneToOne
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @Column(name = "budget")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
