package com.json.json.services;

import com.json.json.models.entites.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;


public interface ProductService  {
    void seedDataFromJson() throws IOException;
}
