package softuni.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PictureJsonUrlDto {
    @Expose
    private String url;

    public PictureJsonUrlDto() {
    }

    @NotNull
    @NotBlank
    public String getUrl() {
        return url;
    }

    public PictureJsonUrlDto setUrl(String url) {
        this.url = url;
        return this;
    }
}
