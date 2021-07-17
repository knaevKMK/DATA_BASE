package com.gameHall.user.models.dto.views;

import com.gameHall.user.models.entities.GameEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDetailsDto {

    private String title;
    private BigDecimal price;
    private String description;
    private String releaseDate;


    public GameDetailsDto() {
    }


    public String getTitle() {
        return title;
    }

    public GameDetailsDto setTitle(String title) {
        this.title = title;
        return this;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public GameDetailsDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameDetailsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public GameDetailsDto setReleaseDate(  String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @Override
    public String toString() {
        return
                "\nTitle: " + title +
                        "\nPrice: " + price +
                        "\nDescription: " + description +
                        "\nReleaseDate: " + releaseDate;
    }
}
