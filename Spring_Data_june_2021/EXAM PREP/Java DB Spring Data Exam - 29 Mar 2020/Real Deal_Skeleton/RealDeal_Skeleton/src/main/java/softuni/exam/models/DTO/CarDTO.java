package softuni.exam.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

public class CarDTO implements Serializable {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private int kilometers;
    @Expose
    private String registeredOn;

    public CarDTO() {
    }

    public String getMake() {
        return make;
    }


    public CarDTO setMake(String make) {
        this.make = make;
        return this;
    }

  public String getModel() {
        return model;
    }

    public CarDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public int getKilometers() {
        return kilometers;
    }


    public CarDTO setKilometers(int kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public CarDTO setRegisteredOn(String registeredOn) {
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
