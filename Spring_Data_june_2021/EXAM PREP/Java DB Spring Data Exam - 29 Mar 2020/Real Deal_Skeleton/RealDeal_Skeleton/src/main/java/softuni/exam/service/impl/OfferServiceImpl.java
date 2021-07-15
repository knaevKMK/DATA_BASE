package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constatnt.Static;
import softuni.exam.models.dto.OfferRootDto;
import softuni.exam.models.entities.CarEntity;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.SellerEntity;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.service.SellerService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final CarService carService;
    private final SellerService sellerService;


    public OfferServiceImpl(OfferRepository offerRepository, FileUtil fileUtil, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, CarService carService, SellerService sellerService) {
        this.offerRepository = offerRepository;

        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.carService = carService;
        this.sellerService = sellerService;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() != 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return this.fileUtil.content(Static.OFFER_FILEPATH, System.lineSeparator());
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        List<String> report = new ArrayList<>();
        OfferRootDto offerRootDto = this.xmlParser.importXMl(OfferRootDto.class, Static.OFFER_FILEPATH);

        offerRootDto.getOfferDtos()
                .forEach(offerDto -> {

                    try {
                        if (!validationUtil.isValid(offerDto)) {
                            throw new Exception();
                        }
                        Offer offer = modelMapper.map(offerDto, Offer.class);
                        SellerEntity sellerEntity = sellerService.findById(offerDto.getSeller().getId());
                        CarEntity carEntity = carService.findCarById(offerDto.getCar().getId());
                        offer.setSeller(sellerEntity)
                                .setCar(carEntity);
//                        offer.setPictures(new HashSet<>(carEntity.getPictures()));
                        offerRepository.saveAndFlush(offer);
                        report.add(String.format("Successfully import offer %s-%s", offer.getAddedOn(), offer.isHasGoldStatus()));

                    } catch (Exception e) {
                        report.add("Invalid offer");
                    }
                });
        System.out.println();
        return String.join(System.lineSeparator(), report);
    }
}
