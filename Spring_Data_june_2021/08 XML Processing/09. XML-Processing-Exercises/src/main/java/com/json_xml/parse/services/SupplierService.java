package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partCarSale.SupplierEntity;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SupplierService {
    void seedData() throws IOException, JAXBException;

    SupplierEntity getRandomById();

    void getLocal() throws JAXBException;
}
