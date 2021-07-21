package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partUserProductCategoriy.CategoryEntity;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedData() throws IOException, JAXBException;

    Set<CategoryEntity> getRandomCategory();

    void categoriesOrderByProductCount() throws JAXBException;
}
