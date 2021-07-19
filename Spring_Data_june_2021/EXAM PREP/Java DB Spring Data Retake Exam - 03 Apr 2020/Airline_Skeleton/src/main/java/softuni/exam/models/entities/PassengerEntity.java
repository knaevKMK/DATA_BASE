package softuni.exam.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "passengers")
public class PassengerEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    //TODO to town
    private TownEntity town;
    private Set<TicketEntity>tickets;


    public PassengerEntity() {
    }
@OneToMany(mappedBy = "passenger", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<TicketEntity> getTickets() {
        return tickets;
    }

    public PassengerEntity setTickets(Set<TicketEntity> tickets) {
        this.tickets = tickets;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "town_id")
    public TownEntity getTown() {
        return town;
    }

    public PassengerEntity setTown(TownEntity town) {
        this.town = town;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PassengerEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PassengerEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public PassengerEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PassengerEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public PassengerEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}
