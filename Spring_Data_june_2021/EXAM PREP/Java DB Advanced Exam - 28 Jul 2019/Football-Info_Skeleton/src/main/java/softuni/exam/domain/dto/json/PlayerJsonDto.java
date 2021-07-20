package softuni.exam.domain.dto.json;

import com.google.gson.annotations.Expose;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class PlayerJsonDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer number;
    @Expose
    private BigDecimal salary;
    @Expose
    private String position;
    @Expose
    private PictureJsonUrlDto picture;
    @Expose
    private TeamJsonNamePictureDto team;

    public PlayerJsonDto() {
    }

    @Size(min = 2, max = 3)
    public String getPosition() {
        return position;
    }

    public PlayerJsonDto setPosition(String position) {
        this.position = position;
        return this;
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public PlayerJsonDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    public PlayerJsonDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotNull
    public Integer getNumber() {
        return number;
    }

    public PlayerJsonDto setNumber(Integer number) {
        this.number = number;
        return this;
    }

    @Positive
    @NotNull

    public BigDecimal getSalary() {
        return salary;
    }

    public PlayerJsonDto setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    @NotNull
    public PictureJsonUrlDto getPicture() {
        return picture;
    }

    public PlayerJsonDto setPicture(PictureJsonUrlDto picture) {
        this.picture = picture;
        return this;
    }

    @NotNull
    public TeamJsonNamePictureDto getTeam() {
        return team;
    }

    public PlayerJsonDto setTeam(TeamJsonNamePictureDto team) {
        this.team = team;
        return this;
    }
}
