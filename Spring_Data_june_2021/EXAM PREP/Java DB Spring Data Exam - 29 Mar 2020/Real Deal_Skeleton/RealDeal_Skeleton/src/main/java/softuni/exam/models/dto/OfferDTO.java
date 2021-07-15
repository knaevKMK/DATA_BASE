package softuni.exam.models.dto;

import softuni.exam.models.entities.CarEntity;
import softuni.exam.models.entities.PictureEntity;
import softuni.exam.models.entities.SellerEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class OfferDTO {
    private BigDecimal price;
    private String description;
    private boolean hasGoldStatus;
    private LocalDateTime addedOn;
    private CarEntity car;
    private SellerEntity seller;
    private Set<PictureEntity> pictures;

    public OfferDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public OfferDTO setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public OfferDTO setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public CarEntity getCar() {
        return car;
    }

    public OfferDTO setCar(CarEntity car) {
        this.car = car;
        return this;
    }

    public SellerEntity getSeller() {
        return seller;
    }

    public OfferDTO setSeller(SellerEntity seller) {
        this.seller = seller;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public OfferDTO setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
