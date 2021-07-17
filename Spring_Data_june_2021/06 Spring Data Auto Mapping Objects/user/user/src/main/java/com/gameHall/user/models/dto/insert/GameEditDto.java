package com.gameHall.user.models.dto.insert;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class GameEditDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;// (YouTube Video Id)
    private String imageUrl;
    private String description;
    private String releaseDate;

    public GameEditDto() {
    }

    public GameEditDto(String... args) {
        this.id = Long.parseLong(args[1]);
        for (int i = 2; i < args.length; i++) {
            String[] row = args[i].split("=");
            switch (row[0]) {
                case "price":
                    setPrice(new BigDecimal(row[1]));
                    break;
                case "size":
                    setSize(Double.parseDouble(row[1]));
                    break;
                case "title":
                    setTitle(row[1]);
                    break;
                case "trailer":
                    setTrailer(row[1]);
                    break;
                case "image":
                    setImageUrl(row[1]);
                    break;
                case "description":
                    setDescription(row[1]);
                    break;
                case "releaseDate":
                    setReleaseDate(row[1]);
                    break;
            }
        }


    }



    public Long getId() {
        return id;
    }

    public GameEditDto setId(Long id) {
        this.id = id;
        return this;
    }

    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,100}$", message = "Invalid title")
    public String getTitle() {
        return title;
    }

    public GameEditDto setTitle(String title) {
        this.title = title;
        return this;
    }

    @DecimalMin(value = "0", message = "invalid price")
    public BigDecimal getPrice() {
        return price;
    }

    public GameEditDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Min(value = 0L, message = "Invalid size")
    public Double getSize() {
        return size;
    }

    public GameEditDto setSize(Double size) {
        this.size = size;
        return this;
    }

    @Size(min = 11, max = 11, message = "trailer url does not match")
    public String getTrailer() {
        return trailer;
    }

    public GameEditDto setTrailer(String trailer) {
        this.trailer = trailer;
        return this;
    }

    @Pattern(regexp = "^http[s]*://[\\w\\W]+", message = "invalid image url")
    public String getImageUrl() {
        return imageUrl;
    }

    public GameEditDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Length(min = 20, message = "Invalid description")
    public String getDescription() {
        return description;
    }

    public GameEditDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public GameEditDto setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
