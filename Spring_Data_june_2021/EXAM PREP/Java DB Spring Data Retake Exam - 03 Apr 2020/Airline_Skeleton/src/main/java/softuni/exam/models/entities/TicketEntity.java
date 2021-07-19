package softuni.exam.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class TicketEntity extends BaseEntity{

    private String serialNumber;
    private BigDecimal price;
    private LocalDateTime takeoff;
    private TownEntity fromTown;
    private PassengerEntity passenger;
    private PlaneEntity plane;
    private TownEntity toTown;

    public TicketEntity() {
    }
@ManyToOne
@JoinColumn(name = "from_town_id")
    public TownEntity getFromTown() {
        return fromTown;
    }

    public TicketEntity setFromTown(TownEntity fromTown) {
        this.fromTown = fromTown;
        return this;
    }
    @ManyToOne(fetch = FetchType.EAGER,cascade =CascadeType.DETACH)
    @JoinColumn(name = "passenger_id")
    public PassengerEntity getPassenger() {
        return passenger;
    }

    public TicketEntity setPassenger(PassengerEntity passenger) {
        this.passenger = passenger;
        return this;
    }
    @ManyToOne
    @JoinColumn(name = "plane_id")
    public PlaneEntity getPlane() {
        return plane;
    }

    public TicketEntity setPlane(PlaneEntity plane) {
        this.plane = plane;
        return this;
    }
    @ManyToOne
    @JoinColumn(name = "to_town_id")
    public TownEntity getToTown() {
        return toTown;
    }

    public TicketEntity setToTown(TownEntity toTown) {
        this.toTown = toTown;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public TicketEntity setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TicketEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getTakeoff() {
        return takeoff;
    }

    public TicketEntity setTakeoff(LocalDateTime takeoff) {
        this.takeoff = takeoff;
        return this;
    }
}
