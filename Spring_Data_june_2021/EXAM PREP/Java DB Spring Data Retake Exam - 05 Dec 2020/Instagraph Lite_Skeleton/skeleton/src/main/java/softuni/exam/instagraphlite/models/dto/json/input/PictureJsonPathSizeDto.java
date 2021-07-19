package softuni.exam.instagraphlite.models.dto.json.input;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PictureJsonPathSizeDto {
    @Expose
    private String path;
    @Expose
    private Double size;

    public PictureJsonPathSizeDto() {
    }
//ToDo validation

    @NotNull

    public String getPath() {
        return path;
    }

    public PictureJsonPathSizeDto setPath(String path) {
        this.path = path;
        return this;
    }

    @NotNull
    @Min(500)
    @Max(60000)
    public Double getSize() {
        return size;
    }

    public PictureJsonPathSizeDto setSize(Double size) {
        this.size = size;
        return this;
    }
}
