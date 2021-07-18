package com.json_xml.parse.services;

import com.json_xml.parse.models.entities.partCarSale.PartEntity;

import java.io.IOException;
import java.util.Set;

public interface PartService {
    void seedData() throws IOException;

    Set<PartEntity> getRandom3To5Parts();


}
