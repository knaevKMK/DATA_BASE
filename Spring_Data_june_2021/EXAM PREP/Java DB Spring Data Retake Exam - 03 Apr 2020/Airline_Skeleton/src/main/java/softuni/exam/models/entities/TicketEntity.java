package softuni.exam.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class TicketEntity extends BaseEntity {

    private String serialNumber;
    private BigDecimal price;
    private LocalDateTime takeoff;
    private TownEntity fromTown;
    private PassengerEntity passenger;
    private PlaneEntity plane;
    private TownEntity toTown;

    public TicketEntity() {
//        this.takeoff = LocalDateTime.now();
    }

//    public TicketEntity(String serialNumber, BigDecimal price, LocalDateTime takeoff, TownEntity fromTown, PassengerEntity passenger, PlaneEntity plane, TownEntity toTown) {
//        this.serialNumber = serialNumber;
//        this.price = price;
//        this.takeoff = takeoff;
//        this.fromTown = fromTown;
//        this.passenger = passenger;
//        this.plane = plane;
//        this.toTown = toTown;
//    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_town_id")
    public TownEntity getFromTown() {
        return fromTown;
    }

    public TicketEntity setFromTown(TownEntity fromTown) {
        this.fromTown = fromTown;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id")
    public PassengerEntity getPassenger() {
        return passenger;
    }

    public TicketEntity setPassenger(PassengerEntity passenger) {
        this.passenger = passenger;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plane_id")
    public PlaneEntity getPlane() {
        return plane;
    }

    public TicketEntity setPlane(PlaneEntity plane) {
        this.plane = plane;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_town_id")
    public TownEntity getToTown() {
        return toTown;
    }

    public TicketEntity setToTown(TownEntity toTown) {
        this.toTown = toTown;
        return this;
    }
@Column(unique = true)
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
        if (takeoff == null) {
            return LocalDateTime.now();
        }
        return takeoff;
    }

    public TicketEntity setTakeoff(LocalDateTime takeoff) {
        if (takeoff == null) {
            this.takeoff = LocalDateTime.now();
        }
        this.takeoff = takeoff;
        return this;
    }
}
