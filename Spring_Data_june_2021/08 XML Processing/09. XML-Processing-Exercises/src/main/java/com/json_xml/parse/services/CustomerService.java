package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partCarSale.CustomerEntity;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CustomerService {
    void seedData() throws IOException, JAXBException;

    CustomerEntity getRandomById();

    void getALLOrderByBirthDateAsc() throws JAXBException;

    void getCustomers() throws JAXBException;
}
