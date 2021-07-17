package com.gameHall.user.repositories;

import com.gameHall.user.models.entities.GameEntity;
import com.gameHall.user.models.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT o FROM OrderEntity  o JOIN o.games g WHERE o.buyer.id= :buyerId AND g.id= :gameId ")
    OrderEntity findAllByUserIdAndGameId(Long buyerId , Long gameId);
}
