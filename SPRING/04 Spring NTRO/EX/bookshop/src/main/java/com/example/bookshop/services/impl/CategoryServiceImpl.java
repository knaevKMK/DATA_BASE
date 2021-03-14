package com.example.bookshop.services.impl;

import com.example.bookshop.constants.GlobalConstants;
import com.example.bookshop.entities.Category;
import com.example.bookshop.repositories.CategoryRepository;
import com.example.bookshop.services.CategoryService;
import com.example.bookshop.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;

import static com.example.bookshop.constants.GlobalConstants.*;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {

        if (this.categoryRepository.count() != 0) {
            return;
        }
        String[] fileContent = this.fileUtil.readFileContent(CATEGORIES_FILE_PATH);
        Arrays.stream(fileContent).forEach(r -> {
            Category category = new Category(r);
            this.categoryRepository.saveAndFlush(category);
        });
    }

    @Override
    public Category findCategoryById(Long id) {
        return this.categoryRepository.getOne(id);
    }

    @Override
    public long getAllCategoriesCount() {
        return this.categoryRepository.count();
    }
}
