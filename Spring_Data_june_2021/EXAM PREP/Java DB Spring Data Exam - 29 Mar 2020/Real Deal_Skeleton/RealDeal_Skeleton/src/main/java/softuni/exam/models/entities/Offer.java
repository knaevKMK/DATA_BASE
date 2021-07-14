package softuni.exam.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private BigDecimal price;
    private String description;
    private boolean hasGoldStatus;
    private LocalDateTime addedOn;
    private CarEntity car;
    private SellerEntity seller;
    private Set<PictureEntity> pictures;

    public Offer() {
    }

    @ManyToMany
    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public Offer setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    @OneToOne
    public CarEntity getCar() {
        return car;
    }

    public Offer setCar(CarEntity car) {
        this.car = car;
        return this;
    }

    @ManyToOne
    public SellerEntity getSeller() {
        return seller;
    }

    public Offer setSeller(SellerEntity seller) {
        this.seller = seller;
        return this;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public Offer setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public Offer setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(columnDefinition = "BOOLEAN")
    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public Offer setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
        return this;
    }

    @Column(name = "added_on", columnDefinition = "DATETIME")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public Offer setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
