package com.gameHall.user.models.dto.insert;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class GameInsertDto {
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;// (YouTube Video Id)
    private String imageUrl;
    private String description;
    private String releaseDate;

    public GameInsertDto() {
    }

    public GameInsertDto(String title,
                         String price,
                         String size,
                         String trailer,
                         String imageUrl,
                         String description,
                         String releaseDate) {
        this.title = title;
        this.price = new BigDecimal(price);
        this.size = Double.parseDouble(size);
        this.trailer = trailer;
        this.imageUrl = imageUrl;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Pattern(regexp = "^[A-Z]{1}[a-z\\w\\W]{2,100}$", message = "Invalid title")
    public String getTitle() {
        return title;
    }

    public GameInsertDto setTitle(String title) {
        this.title = title;
        return this;
    }

    @DecimalMin(value = "0", message = "invalid price")
    public BigDecimal getPrice() {
        return price;
    }

    public GameInsertDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Min(value = 0L, message = "Invalid size")
    public Double getSize() {
        return size;
    }

    public GameInsertDto setSize(Double size) {
        this.size = size;
        return this;
    }

    @Size(min = 11, max = 11, message = "trailer url does not match")
    public String getTrailer() {
        return trailer;
    }

    public GameInsertDto setTrailer(String trailer) {
        this.trailer = trailer;
        return this;
    }

    @Pattern(regexp = "^http[s]*://[\\w\\W]+", message = "invalid image url")
    public String getImageUrl() {
        return imageUrl;
    }

    public GameInsertDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Length(min = 20, message = "Invalid description")
    public String getDescription() {
        return description;
    }

    public GameInsertDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public GameInsertDto setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
