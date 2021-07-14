package com.json.json.services.impl;

import com.google.gson.Gson;
import com.json.json.models.dto.ProductDTO;
import com.json.json.models.entites.ProductEntity;
import com.json.json.repositories.CategoryRepository;
import com.json.json.repositories.ProductRepository;
import com.json.json.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository categoryRepository, Gson gson, ModelMapper modelMapper) {
        this.productRepository = categoryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedDataFromJson() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/files/products.json")));

        Arrays.stream(gson.fromJson(content, ProductDTO[].class))
                .forEach(productDTO -> {
                    ProductEntity productEntity =
                            modelMapper.map(productDTO, ProductEntity.class);
                   this.productRepository.save(productEntity);
                });
    }
}
