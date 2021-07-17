package com.gameHall.user.models.dto.views;

import java.math.BigDecimal;

public class GameViewDto {
    private String title;
    private BigDecimal price;

    public GameViewDto() {
    }

    public String getTitle() {
        return title;
    }

    public GameViewDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public GameViewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
