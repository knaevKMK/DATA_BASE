package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.xml.PictureRootXmlDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.Static.*;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;


    public PictureServiceImpl(PictureRepository pictureRepository, XmlParser xmlParser, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.pictureRepository = pictureRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String importPictures() throws JAXBException {
        List<String> report = new ArrayList<>();
        xmlParser.importXMl(PictureRootXmlDto.class, DIR_XML_PATH + PICTURES_FILE)
                .getPictureDto()
                .forEach(pictureDto -> {

                    try {
                        if (!validatorUtil.isValid(pictureDto)) {
                            throw new Exception();
                        }
                        Picture picture = modelMapper.map(pictureDto, Picture.class);

                        pictureRepository.save(picture);
                        report.add("Successfully imported picture - " + picture.getUrl());
                    } catch (Exception e) {
                        report.add("Invalid picture");
                    }
                });

        return String.join(System.lineSeparator(), report);
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() != 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return fileUtil.readFile(DIR_XML_PATH + PICTURES_FILE);
    }

    @Override
    public Optional<Picture> findByUrl(String url) {
        return pictureRepository.findByUrl(url);
    }

}
