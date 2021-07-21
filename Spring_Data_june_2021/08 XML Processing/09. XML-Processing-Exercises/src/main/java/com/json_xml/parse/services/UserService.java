package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partUserProductCategoriy.UserEntity;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface UserService {
    void seedData() throws IOException, JAXBException;

    UserEntity getRandomUser();

    void findSellersAndSoldProduct() throws JAXBException;

    void getStatisticUsersWithTheirSoldProducts() throws JAXBException;
}
