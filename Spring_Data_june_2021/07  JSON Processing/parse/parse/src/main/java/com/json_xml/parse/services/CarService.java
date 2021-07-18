package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partCarSale.CarEntity;

import java.io.IOException;

public interface CarService {
    void seedData() throws IOException;

    CarEntity getRandomById();

    String getALLByAndOrderByMake(String make);

    String getAllByCar();
}
