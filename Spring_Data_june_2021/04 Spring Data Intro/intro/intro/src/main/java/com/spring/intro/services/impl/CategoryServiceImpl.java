package com.spring.intro.services.impl;


import com.spring.intro.constants.GlobalConstants;
import com.spring.intro.models.entities.CategoryEntity;
import com.spring.intro.repositories.CategoryRepository;
import com.spring.intro.services.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void seedData() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }
        Files.readAllLines(Path.of(GlobalConstants.CATEGORIES_FILE_PATH))
                .stream()
                .filter(r -> !r.isEmpty())
                .forEach(c -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setName(c);
                    categoryRepository.save(category);
                });
    }

    @Override
    public Set<CategoryEntity> getRandomCategories() {
        int count = ThreadLocalRandom.current().nextInt(1, 3);
        List<Long> indexes = new ArrayList<>();
        Set<CategoryEntity> temp = new HashSet<>();
        for (int i = 1; i <= count; i++) {
            long id = ThreadLocalRandom.current().nextLong(1, categoryRepository.count() + 1);
            if (indexes.contains(id)){
                i--;
                continue;
            }
            indexes.add(id);
            temp.add(this.categoryRepository.findById(id).orElse(null));
        }

        return temp;
    }
}
