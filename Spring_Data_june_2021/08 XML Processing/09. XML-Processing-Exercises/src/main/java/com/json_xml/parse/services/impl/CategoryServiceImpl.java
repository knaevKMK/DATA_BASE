package com.json_xml.parse.services.impl;

import com.json_xml.parse.models.dto.partUserProductCategoriy.input.CategoryRootXmlDto;
import com.json_xml.parse.models.dto.partUserProductCategoriy.outDto.CategoryRootDto;
import com.json_xml.parse.models.dto.partUserProductCategoriy.outDto.CategoryViewWithProductCountDto;
import com.json_xml.parse.models.entities.partUserProductCategoriy.CategoryEntity;
import com.json_xml.parse.repositories.CategoryRepository;
import com.json_xml.parse.services.CategoryService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import com.json_xml.parse.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ModelMapper modelMapper,
                               XmlParser xmlParser,
                               ValidationUtil validationUtil,
                               IOUtil ioUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
    }

    private final IOUtil ioUtil;

    @Override
    public void seedData() throws IOException, JAXBException {
        if (categoryRepository.count() != 0) {
            ioUtil.print("category_table data is not empty");
            return;
        }
        ioUtil.print("Data will seed from " + CATEGORY_XML_FILEPATH + "\nPLEASE WAIT...");

        String content = String.join("", ioUtil.readFile(CATEGORY_XML_FILEPATH));

        List<String> report = new ArrayList<>();
        xmlParser.importXMl(CategoryRootXmlDto.class, CATEGORY_XML_FILEPATH)
                .getCategoryXmlNameDtos()
                .forEach(categoryDto -> {
                    try {
                        if (!validationUtil.isValid(categoryDto)) {
                            throw new Exception("Invalid category: " + categoryDto.getName() + System.lineSeparator() +
                                    validationUtil.violation(categoryDto).stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(System.lineSeparator())));
                        }
                        CategoryEntity categoryEntity = modelMapper.map(categoryDto, CategoryEntity.class);
                        categoryRepository.save(categoryEntity);
                        report.add("Category: " + categoryEntity.getName() + " was added");
                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }
                });
        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public Set<CategoryEntity> getRandomCategory() {

        Set<CategoryEntity> categories = new LinkedHashSet<>();
        categories.add(categoryRepository.getById(
                ThreadLocalRandom.current()
                        .nextLong(1, categoryRepository.count() + 1)));
        return categories.isEmpty() ? this.getRandomCategory() : categories;
    }

    @Override
    public void categoriesOrderByProductCount() throws JAXBException {
        CategoryRootDto categoryRootDto = new CategoryRootDto();

        categoryRootDto.setCategoryDtos(categoryRepository.getAllCategoriesOrderByProductsCount()
                .stream().map(categoryEntity -> {
                    CategoryViewWithProductCountDto category =
                            modelMapper.map(categoryEntity, CategoryViewWithProductCountDto.class);
                    category.setProductsCount(categoryEntity.getProducts().size())
                            .setAveragePrice(BigDecimal.valueOf(categoryEntity.getProducts()
                                    .stream()
                                    .mapToDouble(p -> Double.parseDouble(p.getPrice().toPlainString()))
                                    .average().orElse(0)).setScale(2, RoundingMode.HALF_UP))
                            .setTotalRevenue(BigDecimal.valueOf(categoryEntity.getProducts()
                                    .stream()
                                    .mapToDouble(p -> Double.parseDouble(p.getPrice().toPlainString())).sum()).setScale(2, RoundingMode.HALF_UP));
                    return category;
                })
                .collect(Collectors.toSet())
        );
        xmlParser.exportXML(categoryRootDto,OUT_DIR_XML_FILEPATH+OUT_CATEGORY_PRODUCT_COUNT_TOTAL_PRICE);
    }
}
