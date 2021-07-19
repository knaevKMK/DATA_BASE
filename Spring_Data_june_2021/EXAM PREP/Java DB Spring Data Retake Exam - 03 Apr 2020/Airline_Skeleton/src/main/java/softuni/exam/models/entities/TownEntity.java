package softuni.exam.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class TownEntity extends BaseEntity {

    private String name;
    private Integer population;
    private String guide;
    private Set<PassengerEntity> passengers;
    private Set<TicketEntity> incomeTickets;
    private Set<TicketEntity> outGoingTickets;

    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "fromTown", fetch = FetchType.EAGER)
    public Set<TicketEntity> getIncomeTickets() {
        return incomeTickets;
    }

    public TownEntity setIncomeTickets(Set<TicketEntity> incomeTickets) {
        this.incomeTickets = incomeTickets;
        return this;
    }

    @OneToMany(mappedBy = "toTown", fetch = FetchType.EAGER)
    public Set<TicketEntity> getOutGoingTickets() {
        return outGoingTickets;
    }

    public TownEntity setOutGoingTickets(Set<TicketEntity> outGoingTickets) {
        this.outGoingTickets = outGoingTickets;
        return this;
    }

    @OneToMany(mappedBy = "town", fetch = FetchType.EAGER)
    public Set<PassengerEntity> getPassengers() {
        return passengers;
    }

    public TownEntity setPassengers(Set<PassengerEntity> passengers) {
        this.passengers = passengers;
        return this;
    }

    public TownEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPopulation() {
        return population;
    }

    public TownEntity setPopulation(Integer population) {
        this.population = population;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getGuide() {
        return guide;
    }

    public TownEntity setGuide(String guide) {
        this.guide = guide;
        return this;
    }

    public TownEntity() {
    }
}
