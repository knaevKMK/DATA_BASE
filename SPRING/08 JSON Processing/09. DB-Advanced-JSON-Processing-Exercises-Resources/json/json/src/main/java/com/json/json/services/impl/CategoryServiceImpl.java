package com.json.json.services.impl;

import com.google.gson.Gson;
import com.json.json.models.dto.CategoryDTO;
import com.json.json.models.entites.CategoryEntity;
import com.json.json.repositories.CategoryRepository;
import com.json.json.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedDataFromJson() throws IOException {

        String content = String.join(""
                , Files.readAllLines(Path.of("src/main/resources/files/categories.json")));

        Arrays.stream(gson.fromJson(content, CategoryDTO[].class))
                .forEach(categoryDTO -> {
                    System.out.println(categoryDTO.toString());
                    try {
                        CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
                        categoryRepository.save(categoryEntity);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
}
