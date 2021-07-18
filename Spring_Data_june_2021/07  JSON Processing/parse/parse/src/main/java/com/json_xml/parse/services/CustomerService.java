package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partCarSale.CustomerEntity;

import java.io.IOException;

public interface CustomerService {
    void seedData() throws IOException;

    CustomerEntity getRandomById();

    String getALLOrderByBirthDateAsc();

    String getCustomers();
}
