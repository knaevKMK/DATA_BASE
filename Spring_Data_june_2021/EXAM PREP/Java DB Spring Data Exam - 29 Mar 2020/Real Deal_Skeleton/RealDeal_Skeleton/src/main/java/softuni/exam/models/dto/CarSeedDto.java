package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CarSeedDto implements Serializable {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private int kilometers;
    @Expose
    private LocalDate registeredOn;

    public CarSeedDto() {
    }

    @Length(min = 2, max = 20)
    public String getMake() {
        return make;
    }


    public CarSeedDto setMake(String make) {
        this.make = make;
        return this;
    }

    @Length(min = 2, max = 20)
    public String getModel() {
        return model;
    }

    public CarSeedDto setModel(String model) {
        this.model = model;
        return this;
    }

    @Min(0)
    public int getKilometers() {
        return kilometers;
    }


    public CarSeedDto setKilometers(int kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public CarSeedDto setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
        return this;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "make='" + getMake() + '\'' +
                ", model='" + getModel() + '\'' +
                ", kilometers=" + getKilometers() +
                ", registeredOn=" + getRegisteredOn() +
                '}';
    }
}
