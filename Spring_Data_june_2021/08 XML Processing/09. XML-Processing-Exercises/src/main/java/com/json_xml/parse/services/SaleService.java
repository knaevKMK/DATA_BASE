package com.json_xml.parse.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SaleService {
    void seedData() throws IOException;


    void getWithDiscounts() throws JAXBException;
}
