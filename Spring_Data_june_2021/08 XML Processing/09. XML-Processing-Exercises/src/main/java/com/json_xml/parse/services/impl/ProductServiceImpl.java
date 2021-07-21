package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partUserProductCategoriy.input.ProductRootXmlDto;
import com.json_xml.parse.models.dto.partUserProductCategoriy.outDto.ProductOutRootDto;
import com.json_xml.parse.models.dto.partUserProductCategoriy.outDto.ProductXmlNameSellerDto;
import com.json_xml.parse.models.entities.partUserProductCategoriy.ProductEntity;
import com.json_xml.parse.repositories.ProductRepository;
import com.json_xml.parse.services.CategoryService;
import com.json_xml.parse.services.ProductService;
import com.json_xml.parse.services.UserService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import com.json_xml.parse.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
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
    private final XmlParser xmlUtil;
    private ValidationUtil validationUtil;
    private final IOUtil ioUtil;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserService userService, CategoryService categoryService, ModelMapper modelMapper,
                              XmlParser xmlUtil, ValidationUtil validationUtil, IOUtil ioUtil) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.xmlUtil = xmlUtil;

        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
    }

    @Override
    public void seedData() throws IOException, JAXBException {
        if (productRepository.count() != 0) {
            ioUtil.print("product_table data is not empty");
            return;
        }
        ioUtil.print("Data will seed from " + PRODUCT_XML_FILEPATH + "\nPLEASE WAIT...");

        String content = String.join("", ioUtil.readFile(PRODUCT_XML_FILEPATH));

        List<String> report = new ArrayList<>();

        xmlUtil.importXMl(ProductRootXmlDto.class, PRODUCT_XML_FILEPATH)
                .getProductXmlNamePriceDtos()
                .forEach(productDto -> {

                    try {
                        if (!validationUtil.isValid(productDto)) {
                            throw new Exception("Invalid product : " +
                                    productDto.getName() +
                                    System.lineSeparator() +
                                    validationUtil.violation(productDto)
                                            .stream().map(m -> "\t-" + m.getMessage())
                                            .collect(Collectors.joining(System.lineSeparator())));

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
                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }
                });

        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public void productInRangeOrderByPriceWithoutBuyer(BigDecimal bigDecimal, BigDecimal decimal) throws JAXBException {

        ProductOutRootDto productRootXmlDto = new ProductOutRootDto();


        productRootXmlDto
                .setProductXmlNameSellerDtoSet(productRepository
                        .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000))
                        .stream()
                        .map(productEntity ->
                        {
                            ProductXmlNameSellerDto dto =
                                    modelMapper.map(productEntity, ProductXmlNameSellerDto.class);
                            dto.setSeller(String.format("%s%s"
                                    , productEntity.getSeller().getFirstName() == null
                                            ? ""
                                            : productEntity.getSeller().getFirstName() + " "
                                    , productEntity.getSeller().getLastName()));
                            return dto;
                        })
                        .collect(Collectors.toSet()));
        xmlUtil.exportXML(productRootXmlDto, OUT_DIR_XML_FILEPATH + OUT_PRODUCT_IN_RANGE);
    }


}
