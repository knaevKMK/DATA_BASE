package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() != 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return String.join(System.lineSeparator(),
                Files.readAllLines(Path.of("src/main/resources/files/xml/sellers.xml")));
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        return null;
    }
}
