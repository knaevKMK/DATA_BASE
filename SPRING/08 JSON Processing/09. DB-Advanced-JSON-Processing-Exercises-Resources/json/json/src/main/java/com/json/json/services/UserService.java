package com.json.json.services;

import com.json.json.models.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;


public interface UserService  {
    void seedDataFromJson() throws IOException;
}
