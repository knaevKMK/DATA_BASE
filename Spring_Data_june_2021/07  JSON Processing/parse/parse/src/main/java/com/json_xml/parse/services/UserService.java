package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.UserEntity;

import java.io.IOException;

public interface UserService {
    void seedData() throws IOException;

    UserEntity getRandomUser();

    String findSellersAndSoldProduct();

    String getStatisticUsersWithTheirSoldProducts();
}
