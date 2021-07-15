package softuni.exam.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {
    private String make;
    private String model;
    private int kilometers;
    private LocalDate registeredOn;

    public CarEntity() {
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
                '}';
    }
}
