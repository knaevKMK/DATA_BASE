package softuni.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TeamJsonNamePictureDto {
    @Expose
    private String name;
    @Expose
    private PictureJsonUrlDto picture;

    public TeamJsonNamePictureDto() {
    }

    @NotNull
    @NotBlank
    public String getName() {
        return name;
    }

    public TeamJsonNamePictureDto setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    public PictureJsonUrlDto getPicture() {
        return picture;
    }

    public TeamJsonNamePictureDto setPicture(PictureJsonUrlDto picture) {
        this.picture = picture;
        return this;
    }
}
