package hiberspring.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {
    private String name;
    private Integer population;
    private Set<BranchEntity> branches;

    public Town() {
    }

    @OneToMany(mappedBy = "town", fetch = FetchType.EAGER)
    public Set<BranchEntity> getBranches() {
        return branches;
    }

    public void setBranches(Set<BranchEntity> branches) {
        this.branches = branches;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
