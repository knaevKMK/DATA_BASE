package com.json_xml.parse.services;

import java.io.IOException;

public interface SaleService {
    void seedData() throws IOException;


    String getWithDiscounts();
}
