package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constatnt.Static;
import softuni.exam.models.dto.SellerRootDto;
import softuni.exam.models.entities.SellerEntity;
import softuni.exam.models.enums.RatingEnum;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, XmlParser xmlParser, FileUtil fileUtil, ValidationUtil validationUtil) {

        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;

    }

    @Override
    public boolean areImported() {
        return sellerRepository.count() != 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return this.fileUtil.content(Static.SELLER_FILEPATH, "");
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        List<String> report = new ArrayList<>();

        SellerRootDto sellerRootDto = this.xmlParser.importXMl(SellerRootDto.class, Static.SELLER_FILEPATH);
        sellerRootDto.getSellersDto().forEach(sellerDto -> {

            try {
                RatingEnum rating = RatingEnum.valueOf(sellerDto.getRating());
                if (
//                        this.sellerRepository.findByEmail(sellerDto.getEmail()).isPresent() ||
                        !this.validationUtil.isValid(sellerDto)) {
                    throw new Exception();
                }
                SellerEntity sellerEntity = modelMapper.map(sellerDto, SellerEntity.class);

                this.sellerRepository.save(sellerEntity);
                report.add(String.format("Successfully import seller %s - %s"
                        , sellerEntity.getLastName()
                        , sellerEntity.getEmail()));
            } catch (Exception e) {
//                report.add(sellerDto.toString());
                report.add("Invalid seller");
            }

        });

        return String.join(System.lineSeparator(), report);
//        return  report.size()+"";
    }

    @Override
    public SellerEntity findById(int id) {

        return sellerRepository.findById(id).get();
    }
}
