package softuni.exam.service;

import softuni.exam.models.entities.SellerEntity;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

//ToDo - Before start App implement this Service and set areImported to return false
public interface SellerService {
    
    boolean areImported();

    String readSellersFromFile() throws IOException;

    String importSellers() throws IOException, JAXBException;

    SellerEntity findById(int id);
}
