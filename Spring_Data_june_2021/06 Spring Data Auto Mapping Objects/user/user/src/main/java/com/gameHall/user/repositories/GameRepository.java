package com.gameHall.user.repositories;

import com.gameHall.user.models.entities.GameEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {


    Optional<GameEntity> findByTitle(String title);

    //    @Query(value =
//            "SELECT * FROM games as g  JOIN orders_games og on g.id = og.games_id " +
//                    ""
//            , nativeQuery = true)
    @Query("SELECT g FROM OrderEntity o JOIN o.games g  WHERE o.buyer.id= :userId")
    Set<GameEntity> findAllByUsersId(Long userId);

}
