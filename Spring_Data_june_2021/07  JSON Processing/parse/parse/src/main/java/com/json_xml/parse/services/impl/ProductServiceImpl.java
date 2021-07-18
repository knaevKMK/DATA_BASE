package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partUserProductCategoriy.input.ProductInFromJsonDto;
import com.json_xml.parse.models.dto.partUserProductCategoriy.outDto.ProductViewJsonDto;
import com.json_xml.parse.models.entities.partUserProductCategoriy.ProductEntity;
import com.json_xml.parse.repositories.ProductRepository;
import com.json_xml.parse.services.CategoryService;
import com.json_xml.parse.services.ProductService;
import com.json_xml.parse.services.UserService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private ValidationUtil validationUtil;
    private final IOUtil ioUtil;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserService userService, CategoryService categoryService, ModelMapper modelMapper,
                              Gson gson,
                              ValidationUtil validationUtil, IOUtil ioUtil) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
    }

    @Override
    public void seedData() throws IOException {
        if (productRepository.count() != 0) {
            ioUtil.print("product_table data is not empty");
            return;
        }
        ioUtil.print("Data will seed from " + PRODUCT_JSON_FILEPATH+"\nPLEASE WAIT...");

        String content = String.join("", ioUtil.readFile(PRODUCT_JSON_FILEPATH));

        List<String> report = new ArrayList<>();

        Arrays.stream(gson.fromJson(content, ProductInFromJsonDto[].class))
                .forEach(productDto -> {
                    if (!validationUtil.isValid(productDto)) {
                        report.add("Invalid product : " +
                                productDto.getName() +
                                System.lineSeparator() +
                                validationUtil.violation(productDto)
                                        .stream().map(m -> "\t-" + m.getMessage())
                                        .collect(Collectors.joining(System.lineSeparator())));
                        return;
                    }


                    ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
                    productEntity
                            .setSeller(userService.getRandomUser())
                            .setCategories(categoryService.getRandomCategory());
                    if (productEntity.getSeller().getId().intValue() > 10) {
                        productEntity.setBuyer(userService.getRandomUser());
                    }

                    productRepository.save(productEntity);
                    report.add("Product: " + productEntity.getName() + " was added");
                });

        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public String productInRangeOrderByPriceWithoutBuyer(BigDecimal bigDecimal, BigDecimal decimal) {
        return gson.toJson(productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000))
                .stream()
                .map(productEntity ->
                {
                    ProductViewJsonDto productViewJsonDto = modelMapper.map(productEntity, ProductViewJsonDto.class);
                    productViewJsonDto.set_seller(String.format("%s%s"
                            , productEntity.getSeller().getFirstName()==null? "":productEntity.getSeller().getFirstName()+" "
                            , productEntity.getSeller().getLastName()));
                    return productViewJsonDto;
                })
                .collect(Collectors.toList()))
                ;

    }


}
