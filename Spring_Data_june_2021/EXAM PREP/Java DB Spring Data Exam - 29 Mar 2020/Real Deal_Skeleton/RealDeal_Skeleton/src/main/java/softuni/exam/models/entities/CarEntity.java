package softuni.exam.models.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {
    private String make;
    private String model;
    private int kilometers;
    private LocalDate registeredOn;
    private Set<PictureEntity> pictures;
    private Set<Offer> offers;

    public CarEntity() {
    }

    @OneToMany(mappedBy = "car",fetch = FetchType.EAGER)

    public Set<Offer> getOffers() {
        return offers;
    }

    public CarEntity setOffers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }

    @OneToMany(mappedBy = "car",fetch = FetchType.EAGER)
    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public CarEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    @Column
    public String getMake() {
        return make;
    }

    public CarEntity setMake(String make) {
        this.make = make;
        return this;
    }

    @Column
    public String getModel() {
        return model;
    }

    public CarEntity setModel(String model) {
        this.model = model;
        return this;
    }

    @Column
    public int getKilometers() {
        return kilometers;
    }

    public CarEntity setKilometers(int kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    @Column(name = "registered_on", columnDefinition = "DATE")
    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public CarEntity setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", kilometers=" + kilometers +
                ", registeredOn=" + registeredOn +
                "pics"+pictures.stream().map(PictureEntity::toString).collect(Collectors.joining())+
                '}';
    }
}
