package softuni.exam.models.dto.input;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownJsonDto {
    @Expose
    private String name;
    @Expose
    private Integer population;
    @Expose
    private String guide;

    public TownJsonDto() {
    }

    @Size(min = 2)
    public String getName() {
        return name;
    }

    public TownJsonDto setName(String name) {
        this.name = name;
        return this;
    }

    @Positive
    public Integer getPopulation() {
        return population;
    }

    public TownJsonDto setPopulation(Integer population) {
        this.population = population;
        return this;
    }

    public String getGuide() {
        return guide;
    }

    public TownJsonDto setGuide(String guide) {
        this.guide = guide;
        return this;
    }
}
