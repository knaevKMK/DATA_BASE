package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.constants.Paths;
import com.json_xml.parse.models.dto.input.ProductInFromJsonDto;
import com.json_xml.parse.models.entities.ProductEntity;
import com.json_xml.parse.repositories.ProductRepository;
import com.json_xml.parse.services.ProductService;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private ValidationUtil validationUtil;

    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper,
                              Gson gson,
                              ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedData() throws IOException {

        String content =
                String.join("",
                        Files.readAllLines(Path.of(Paths.PRODUCT_JSON_FILEPATH)));

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


                    ProductEntity productEntity= modelMapper.map(productDto,ProductEntity.class);
                    productRepository.save(productEntity);
                    report.add("Product: "+ productEntity.getName()+ " was added");
                });

        System.out.println(String.join(System.lineSeparator(), report));
    }
}
