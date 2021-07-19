package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.input.xml.PlaneRootXmlDto;
import softuni.exam.models.entities.PlaneEntity;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.IOUtil;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static softuni.exam.constants.Path.DIR_XML_PATH;
import static softuni.exam.constants.Path.PLANE_XML_FILE_PATH;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final IOUtil ioUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public PlaneServiceImpl(PlaneRepository planeRepository, Gson gson, ValidationUtil validationUtil, IOUtil ioUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return planeRepository.count() != 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        return String.join("", ioUtil.readFile(DIR_XML_PATH + PLANE_XML_FILE_PATH));
    }

    @Override
    public String importPlanes() throws JAXBException {
        List<String> report = new ArrayList<>();

        PlaneRootXmlDto planeRootXmlDto = xmlParser.importXMl(PlaneRootXmlDto.class, DIR_XML_PATH + PLANE_XML_FILE_PATH);

        planeRootXmlDto.getPlaneXmlDtos()
                .forEach(planeXmlDto -> {
                    if (!validationUtil.isValid(planeXmlDto)) {
                        report.add("Invalid Plane");
                        return;
                    }
                    PlaneEntity plane = modelMapper.map(planeXmlDto, PlaneEntity.class);
                   planeRepository.save(plane);
                    report.add("Successfully imported " + plane.getRegisterNumber());
                });

        return String.join(System.lineSeparator(), report);
    }

    @Override
    public PlaneEntity findByRegisterNumber(String registerNumber) {

        return  planeRepository.findByRegisterNumber(registerNumber);
    }
}
