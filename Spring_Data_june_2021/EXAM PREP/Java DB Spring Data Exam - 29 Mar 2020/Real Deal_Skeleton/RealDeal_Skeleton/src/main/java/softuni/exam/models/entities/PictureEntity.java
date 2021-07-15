package softuni.exam.models.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {
    private String name;
    private LocalDateTime dateAndTime;
    private CarEntity car;
    private Set<Offer> offers;

    public PictureEntity() {
    }

    @ManyToMany(mappedBy = "pictures",fetch = FetchType.EAGER)
    public Set<Offer> getOffers() {
        return offers;
    }

    public PictureEntity setOffers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "car_id")
    public CarEntity getCar() {
        return car;
    }

    public PictureEntity setCar(CarEntity car) {
        this.car = car;
        return this;
    }

    @Column
    public String getName() {
        return name;
    }

    public PictureEntity setName(String name) {
        this.name = name;
        return this;
    }

    public PictureEntity setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
        return this;
    }

    @Column(name = "date_and_time")
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return "PictureEntity{" +
                "name='" + name + '\'' +
                ", dateAndTime=" + dateAndTime +
                ", car=" + car +
                '}';
    }
}
