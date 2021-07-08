package com.spring.intro.services;

import com.spring.intro.models.entities.CategoryEntity;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedData() throws IOException;

    Set<CategoryEntity> getRandomCategories();
}
