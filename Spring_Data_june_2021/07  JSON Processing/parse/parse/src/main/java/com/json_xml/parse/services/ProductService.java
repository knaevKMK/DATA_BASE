package com.json_xml.parse.services;

import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {
    void seedData() throws IOException;

    String productInRangeOrderByPriceWithoutBuyer(BigDecimal bigDecimal, BigDecimal decimal);



}
