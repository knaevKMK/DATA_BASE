package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partCarSale.SupplierEntity;

import java.io.IOException;

public interface SupplierService {
    void seedData() throws IOException;

    SupplierEntity getRandomById();

    String getLocal();
}
