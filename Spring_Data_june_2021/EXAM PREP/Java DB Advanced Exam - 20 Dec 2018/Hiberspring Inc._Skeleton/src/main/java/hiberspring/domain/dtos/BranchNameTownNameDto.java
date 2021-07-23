package hiberspring.domain.dtos;

import com.google.gson.annotations.Expose;

public class BranchNameTownNameDto {
    @Expose
    private String name;
    @Expose
    private String town;

    public BranchNameTownNameDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
