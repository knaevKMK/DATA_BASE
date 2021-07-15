package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class PictureDTO {
    @Expose
    private String name;
    @Expose
    private String dateAndTime;
    @Expose
    private int car;

    public PictureDTO() {
    }
    @Length(min = 2, max = 20)
    public String getName() {
        return name;
    }

    public PictureDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public PictureDTO setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
        return this;
    }

    public int getCar() {
        return car;
    }

    public PictureDTO setCar(int car) {
        this.car = car;
        return this;
    }

    @Override
    public String toString() {
        return "PictureDTO{" +
                "name='" + name + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", car=" + car +
                '}';
    }
}
