package sprin_user_system.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {
    private String name;
    private Country country;
    private Set<User> born;
    private Set<User> live;

    public Town() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(optional = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "bornInTown")
    public Set<User> getBorn() {
        return born;
    }

    public void setBorn(Set<User> born) {
        this.born = born;
    }

    @OneToMany(mappedBy = "liveInTown")
    public Set<User> getLive() {
        return live;
    }

    public void setLive(Set<User> live) {
        this.live = live;
    }
}
