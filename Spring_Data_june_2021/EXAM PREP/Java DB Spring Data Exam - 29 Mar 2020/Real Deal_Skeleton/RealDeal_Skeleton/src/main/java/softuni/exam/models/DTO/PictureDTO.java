package softuni.exam.models.DTO;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entities.CarEntity;

import java.time.LocalDateTime;

public class PictureDTO {
    @Expose
    private String name;

    private LocalDateTime dateAndTime;
    @Expose
    private int car_id;

    public PictureDTO() {
    }

    public String getName() {
        return name;
    }

    public PictureDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public PictureDTO setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
        return this;
    }

    public int getCar() {
        return car_id;
    }

    public PictureDTO setCar(int car) {
        this.car_id = car;
        return this;
    }
}
