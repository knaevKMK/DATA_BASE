package com.gameHall.user.models.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
  private UserEntity buyer;
    private Set<GameEntity> games;
    private boolean isCompleted;

    public OrderEntity() {

    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public OrderEntity setCompleted(boolean completed) {
        isCompleted = completed;
        return this;
    }

    @ManyToOne
    public UserEntity getBuyer() {
        return buyer;
    }

    public OrderEntity setBuyer(UserEntity buyer) {
        this.buyer = buyer;
        return this;
    }

    @ManyToMany
    public Set<GameEntity> getGames() {
        return games;
    }


    public OrderEntity setGames(Set<GameEntity> games) {
        this.games = games;
        return this;
    }
}
