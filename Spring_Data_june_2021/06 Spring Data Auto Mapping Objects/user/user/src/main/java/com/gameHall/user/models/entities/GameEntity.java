package com.gameHall.user.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
public class GameEntity extends BaseEntity {

    private String title;
    private String trailer;// (YouTube Video Id)
    private String imageUrl;
    private Double size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
    private Set<OrderEntity> oreders;


    @ManyToMany(mappedBy = "games",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<OrderEntity> getOreders() {
        return oreders;
    }

    public GameEntity setOreders(Set<OrderEntity> oreders) {
        this.oreders = oreders;
        return this;
    }

    public GameEntity() {
    }


    public String getTitle() {
        return title;
    }

    public GameEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTrailer() {
        return trailer;
    }

    public GameEntity setTrailer(String trailer) {
        this.trailer = trailer;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public GameEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Double getSize() {
        return size;
    }

    public GameEntity setSize(Double size) {
        this.size = size;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public GameEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GameEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public GameEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "title='" + title + '\'' +
                ", trailer='" + trailer + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
