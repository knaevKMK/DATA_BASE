package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partCarSale.CarEntity;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CarService {
    void seedData() throws IOException, JAXBException;

    CarEntity getRandomById();

    void getALLByAndOrderByMake(String make) throws JAXBException;

    void getAllByCar() throws JAXBException;
}
