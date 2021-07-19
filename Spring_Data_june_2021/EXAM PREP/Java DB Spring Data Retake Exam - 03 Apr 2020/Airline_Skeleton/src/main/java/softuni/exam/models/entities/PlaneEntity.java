package softuni.exam.models.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "planes")
public class PlaneEntity extends BaseEntity {
    private String registerNumber;
    private int capacity;
    private String airline;
    private Set<TicketEntity> tickets;

    public PlaneEntity() {
    }

    @OneToMany(mappedBy = "plane", fetch = FetchType.EAGER)
    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public PlaneEntity setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
        return this;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public PlaneEntity setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public PlaneEntity setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getAirline() {
        return airline;
    }

    public PlaneEntity setAirline(String airline) {
        this.airline = airline;
        return this;
    }
}
