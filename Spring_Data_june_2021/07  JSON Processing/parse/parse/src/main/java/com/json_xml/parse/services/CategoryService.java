package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partUserProductCategoriy.CategoryEntity;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedData() throws IOException;

    Set<CategoryEntity> getRandomCategory();

    String categoriesOrderByProductCount();
}
