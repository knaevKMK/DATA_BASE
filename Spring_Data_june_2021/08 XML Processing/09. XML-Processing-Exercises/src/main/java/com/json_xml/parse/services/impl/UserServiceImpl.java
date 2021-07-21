package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partUserProductCategoriy.input.UserRootXmlDto;
import com.json_xml.parse.models.dto.partUserProductCategoriy.outDto.*;
import com.json_xml.parse.models.entities.partUserProductCategoriy.UserEntity;
import com.json_xml.parse.repositories.UserRepository;
import com.json_xml.parse.services.UserService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import com.json_xml.parse.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final XmlParser xmlParser;
    private final IOUtil ioUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validator, Gson gson, XmlParser xmlParser, IOUtil ioUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.xmlParser = xmlParser;
//        this.gson = gson;
        this.ioUtil = ioUtil;
    }

    @Override
    public void seedData() throws IOException, JAXBException {
        if (userRepository.count() != 0) {
            ioUtil.print("user_table data is not empty");
            return;
        }
        ioUtil.print("Data will seed from " + USER_XML_FILEPATH + "\nPLEASE WAIT...");


        String content = String.join("", ioUtil.readFile(USER_XML_FILEPATH));
        List<String> report = new ArrayList<>();

        xmlParser.importXMl(UserRootXmlDto.class, USER_XML_FILEPATH)
                .getUserXmlDtos()
                .forEach(userDto -> {

                    try {
                        if (!validator.isValid(userDto)) {
                            throw new Exception("Invalid user:" + userDto.getLastName() + "\n"
                                    + validator.violation(userDto).stream()
                                    .map(ConstraintViolation::getMessage)
                                    .collect(Collectors.joining(System.lineSeparator())));
                        }

                        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
                        userRepository.save(userEntity);
                        report.add("Successfully imported User:" + userEntity.getLastName());

                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }
                });

        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public UserEntity getRandomUser() {

        long id = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void findSellersAndSoldProduct() throws JAXBException {
        UserRootDto userRootDto = new UserRootDto();

        userRootDto.setUserDtos(userRepository.findAllByProductsHasBuyer()
                .stream()
                .map(seller -> {
                    UserFirstLastNameSoldProductsDTO sellerDto =
                            modelMapper.map(seller, UserFirstLastNameSoldProductsDTO.class);

                    sellerDto.setSoldProducts(new ProductRootDto())
                            .getSoldProducts().setProductViewWithBuyersDtoSet(
                            seller.getProducts()
                                    .stream()
                                    .map(product -> {
                                        ProductViewWithBuyersDto productDto =
                                                modelMapper.map(product, ProductViewWithBuyersDto.class);
                                        productDto.setBuyerFirstName(product.getBuyer().getFirstName())
                                                .setBuyerLastName(product.getBuyer().getFirstName());
                                        return productDto;
                                    })
                                    .collect(Collectors.toSet()));


                    return sellerDto;
                })
                .collect(Collectors.toSet()));
        xmlParser.exportXML(userRootDto, OUT_DIR_XML_FILEPATH + OUT_SELLER_SOLD_PRODUCT);
    }

    @Override
    public void getStatisticUsersWithTheirSoldProducts() throws JAXBException {
        UserRoot2Dto userRootDto = new UserRoot2Dto();
        Set<UserEntity> sellers = userRepository.findAllByProductsHasBuyer();

        userRootDto.setCount(sellers.size())
                .setUserDtos(sellers.stream()
                        .map(seller -> {
                            UserFirstLastNameSoldProducts2DTO seller1 = modelMapper.map(seller, UserFirstLastNameSoldProducts2DTO.class);

                            seller1.setSoldProducts(new ProductRoot2Dto());
                            seller1.getSoldProducts().setCount(seller.getProducts().size());
                            seller1.getSoldProducts().setProductViewWithBuyersDtoSet(
                                    seller.getProducts()
                                            .stream().map(product -> modelMapper.map(product, ProductVieNamePricewDto.class))
                                            .collect(Collectors.toSet()));

                            return seller1;
                        })
                        .sorted((a, s) -> Integer.compare(s.getSoldProducts().getCount(), a.getSoldProducts().getCount()))
                        .collect(Collectors.toSet()));

        xmlParser.exportXML(userRootDto, OUT_DIR_XML_FILEPATH + OUT_USER_PRODUCT);
    }
}
