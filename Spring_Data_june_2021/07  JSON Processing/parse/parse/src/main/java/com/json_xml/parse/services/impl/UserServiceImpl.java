package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partUserProductCategoriy.input.UserInFromFileJsonDto;
import com.json_xml.parse.models.dto.partUserProductCategoriy.outDto.*;
import com.json_xml.parse.models.entities.partUserProductCategoriy.UserEntity;
import com.json_xml.parse.repositories.UserRepository;
import com.json_xml.parse.services.UserService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
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
    private final Gson gson;
    private final IOUtil ioUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validator, Gson gson, IOUtil ioUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
        this.ioUtil = ioUtil;
    }

    @Override
    public void seedData() throws IOException {
        if (userRepository.count() != 0) {
            ioUtil.print("user_table data is not empty");
            return;
        }
        ioUtil.print("Data will seed from " + USER_JSON_FILEPATH + "\nPLEASE WAIT...");


        String content = String.join("", ioUtil.readFile(USER_JSON_FILEPATH));
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(content, UserInFromFileJsonDto[].class))
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
    public String findSellersAndSoldProduct() {
        return gson.toJson(userRepository.findAllByProductsHasBuyer()
                .stream()
                .map(seller -> modelMapper.map(seller, SellerWithProductViewDto.class))
                .collect(Collectors.toList()));

    }

    @Override
    public String getStatisticUsersWithTheirSoldProducts() {
        TotalUserCountWithSoldProductsDto totalUser = new TotalUserCountWithSoldProductsDto();

        Set<UserEntity> sellers = userRepository.findAllByProductsHasBuyer();
        totalUser.setUsersCount(sellers.size())
                .setUsers(sellers
                        .stream()
                        .map(seller -> {
                            UserFirstLastNameAgeSoldProductsDTO seller1 =
                                    modelMapper.map(seller, UserFirstLastNameAgeSoldProductsDTO.class);
                            seller1.setSoldProducts(new SoldProductCountAndListDto());

                            SoldProductCountAndListDto soldProducts = seller1.getSoldProducts();
                            soldProducts.setCount(seller.getProducts().size());
                            soldProducts.setProducts(seller.getProducts()
                                    .stream()
                                    .map(product -> modelMapper.map(product, ProductNameAgeDto.class))
                                    .collect(Collectors.toSet()));
                            return seller1;
                        })
                        .sorted((a, s) -> Integer.compare(s.getSoldProducts().getCount(), a.getSoldProducts().getCount()))
                        .collect(Collectors.toList()));

        return gson.toJson(totalUser);
    }
}
