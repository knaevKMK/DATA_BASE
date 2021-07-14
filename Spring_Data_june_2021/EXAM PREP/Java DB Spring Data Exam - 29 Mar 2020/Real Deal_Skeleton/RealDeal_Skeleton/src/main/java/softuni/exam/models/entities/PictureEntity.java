package softuni.exam.models.entities;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {
    private String name;
    private LocalDateTime dateAndTime;
    private CarEntity car;

    public PictureEntity() {
    }

    @OneToOne
    public CarEntity getCar() {
        return car;
    }

    public PictureEntity setCar(CarEntity car) {
        this.car = car;
        return this;
    }

    @Column(columnDefinition = "VARCHAR(20)", unique = true)
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
}
