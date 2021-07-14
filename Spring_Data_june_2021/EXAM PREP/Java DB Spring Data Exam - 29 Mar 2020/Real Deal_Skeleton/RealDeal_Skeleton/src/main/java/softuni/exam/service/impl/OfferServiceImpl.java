package softuni.exam.service.impl;

import org.springframework.stereotype.Service;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count()!=0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return String.join(System.lineSeparator(),
                Files.readAllLines(Path.of("src/main/resources/files/xml/offers.xml")));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        return null;
    }
}
