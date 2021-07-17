package com.gameHall.user.repositories;

import com.gameHall.user.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
    Optional<UserEntity> findByEmail(String email);
}
