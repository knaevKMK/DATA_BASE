package softuni.exam.service;


import softuni.exam.models.entities.PlaneEntity;

import javax.xml.bind.JAXBException;
import java.io.IOException;

//ToDo - Before start App implement this Service and set areImported to return false
public interface PlaneService {

    boolean areImported();

    String readPlanesFileContent() throws IOException;
	
	String importPlanes() throws Exception;

    PlaneEntity findByRegisterNumber(String registerNumber);
}
