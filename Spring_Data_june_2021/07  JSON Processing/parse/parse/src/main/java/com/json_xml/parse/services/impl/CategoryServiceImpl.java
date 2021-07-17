package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.constants.Paths;
import com.json_xml.parse.models.dto.input.CategoryInFromJsonDto;
import com.json_xml.parse.models.dto.outDto.CategoryViewWithProductCountDto;
import com.json_xml.parse.models.entities.CategoryEntity;
import com.json_xml.parse.repositories.CategoryRepository;
import com.json_xml.parse.services.CategoryService;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedData() throws IOException {
        if (categoryRepository.count() != 0) {
            System.out.println("Your data is not empty");
            return;
        }
        String content = String.join("", Files.readAllLines(Path.of(Paths.CATEGORY_JSON_FILEPATH)));

        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(content, CategoryInFromJsonDto[].class))
                .forEach(categoryDto -> {

                    if (!validationUtil.isValid(categoryDto)) {
                        report.add("Invalid category: " + categoryDto.getName() + System.lineSeparator() +
                                validationUtil.violation(categoryDto).stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(System.lineSeparator())));
                        return;
                    }
                    CategoryEntity categoryEntity = modelMapper.map(categoryDto, CategoryEntity.class);
                    categoryRepository.save(categoryEntity);
                    report.add("Category: " + categoryEntity.getName() + " was added");

                });
        System.out.println(String.join(System.lineSeparator(), report));
    }

    @Override
    public Set<CategoryEntity> getRandomCategory() {
//        int size = ThreadLocalRandom.current().nextInt(3);
        Set<CategoryEntity> categories = new LinkedHashSet<>();
//        for (int i = 0; i <= size; i++) {
        categories.add(categoryRepository.getById(
                ThreadLocalRandom.current()
                        .nextLong(1, categoryRepository.count() + 1)));
//        }
        return categories.isEmpty() ? null : categories;
    }

    @Override
    public String categoriesOrderByProductCount() {

        return gson.toJson(categoryRepository.getAllCategoriesOrderByProductsCount()
                .stream().map(categoryEntity -> {
                    CategoryViewWithProductCountDto category = modelMapper.map(categoryEntity, CategoryViewWithProductCountDto.class);
                    category.setProductsCount(categoryEntity.getProducts().size())
                            .setAveragePrice(BigDecimal.valueOf(categoryEntity.getProducts()
                                    .stream()
                                    .mapToDouble(p -> Double.parseDouble(p.getPrice().toPlainString()))
                                    .average().orElse(0)))
                            .setTotalRevenue(BigDecimal.valueOf(categoryEntity.getProducts()
                                    .stream()
                                    .mapToDouble(p -> Double.parseDouble(p.getPrice().toPlainString())).sum()));
                    return category;
                })
                .collect(Collectors.toList())
        );
    }
}
