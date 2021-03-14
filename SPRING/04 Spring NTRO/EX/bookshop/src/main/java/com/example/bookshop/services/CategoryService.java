package com.example.bookshop.services;

import com.example.bookshop.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    Category findCategoryById(Long id);

    long getAllCategoriesCount();
}
