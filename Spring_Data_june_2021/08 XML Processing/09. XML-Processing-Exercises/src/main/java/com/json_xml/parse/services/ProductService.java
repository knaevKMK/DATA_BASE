package com.json_xml.parse.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {
    void seedData() throws IOException, JAXBException;

    void productInRangeOrderByPriceWithoutBuyer(BigDecimal bigDecimal, BigDecimal decimal) throws JAXBException;



}
