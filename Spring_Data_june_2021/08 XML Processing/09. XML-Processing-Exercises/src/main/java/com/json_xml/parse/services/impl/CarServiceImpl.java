package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partCarSale.input.CarRootDto;
import com.json_xml.parse.models.dto.partCarSale.out.*;
import com.json_xml.parse.models.entities.partCarSale.CarEntity;
import com.json_xml.parse.models.entities.partCarSale.PartEntity;
import com.json_xml.parse.repositories.CarRepository;
import com.json_xml.parse.services.CarService;
import com.json_xml.parse.services.PartService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import com.json_xml.parse.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.*;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final IOUtil ioUtil;
    private final ValidationUtil validation;
    private final XmlParser xmlParser;
    private final PartService partService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, IOUtil ioUtil, ValidationUtil validation, Gson gson, XmlParser xmlParser, PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.ioUtil = ioUtil;
        this.validation = validation;
        this.xmlParser = xmlParser;

        this.partService = partService;
    }


    @Override
    public void seedData() throws IOException, JAXBException {
        if (carRepository.count() != 0) {
            ioUtil.print("car_table data is not Empty");
            return;
        }


        ioUtil.print("\nData will seed from " + CAR_XML_FILEPATH + "\nPLEASE WAIT...\n" +
                "The process is too long! Thanks for your patience");
        List<String> report = new ArrayList<>();
        String content = String.join("", ioUtil.readFile(CAR_XML_FILEPATH));
        xmlParser.importXMl(CarRootDto.class, CAR_XML_FILEPATH)
                .getCarXmlMakeModelDistanceDtos()
                .forEach(carIn -> {
                    try {
                        if (!validation.isValid(carIn)) {
                            throw new Exception("\nInvalid car: " + carIn.getMake() + "\n" +
                                    validation.violation(carIn).stream().map(ConstraintViolation::getMessage)
                                            .collect(Collectors.joining(System.lineSeparator())));
                        }
                        CarEntity car = modelMapper.map(carIn, CarEntity.class);
                        //Add between 3-5 parts
                        Set<PartEntity> parts = partService.getRandom3To5Parts();
                        car.setParts(parts);
                        carRepository.save(car);
                        report.add("Success add in db car: " + car.getMake());
                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }
                });

        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public CarEntity getRandomById() {

        long id = ThreadLocalRandom.current().nextLong(carRepository.count()) + 1;
        CarEntity car = carRepository.findById(id).orElse(null);
        if (car == null) {
            car = getRandomById();
        }
        return car;
    }

    @Override
    public void getALLByAndOrderByMake(String make) throws JAXBException {
//ex_6
        com.json_xml.parse.models.dto.partCarSale.out.CarRootDto carRootDto = new com.json_xml.parse.models.dto.partCarSale.out.CarRootDto();
        carRootDto.setCarDtos(
                carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota")
                        .stream().map(car -> {
                    CarViewMakeModelTravelDistDto carView =
                            modelMapper.map(car, CarViewMakeModelTravelDistDto.class);

                    return carView;
                })
                        .collect(Collectors.toSet())
        );
        xmlParser.exportXML(carRootDto, OUT_DIR_XML_FILEPATH + OUT_CAR_TOYOTA_FILE);
    }

    @Override
    public void getAllByCar() throws JAXBException {
// ex_8
        CarRoot2Dto carRoot2Dto = new CarRoot2Dto();

        carRoot2Dto.setCarDtos(carRepository.findAll()
                .stream()
                .map(car -> {
                    CarViewMakeModelTravelDistPartsListDto carDto = modelMapper.map(car, CarViewMakeModelTravelDistPartsListDto.class);

                    carDto.setParts(new PartRootOutDto());
                    carDto.getParts().setPartDtos(
                            car.getParts()
                                    .stream()
                                    .map(p -> modelMapper.map(p, PartViewNamePriceDTO.class))
                                    .collect(Collectors.toSet()));

                    return carDto;
                }).collect(Collectors.toSet())
        );

        xmlParser.exportXML(carRoot2Dto, OUT_DIR_XML_FILEPATH+OUT_CAR_PARTS_FILE);
    }
}
