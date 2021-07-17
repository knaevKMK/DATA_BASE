package com.json_xml.parse.repositories;

import com.json_xml.parse.models.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query ("SELECT c FROM CategoryEntity c ORDER BY size(c.products) DESC ")
    List<CategoryEntity> getAllCategoriesOrderByProductsCount();
}
