package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partCarSale.input.CarInputJsonDto;
import com.json_xml.parse.models.dto.partCarSale.out.CarByMakeSortByModelAndTravelDistDto;
import com.json_xml.parse.models.dto.partCarSale.out.CarViewCarPartsDto;
import com.json_xml.parse.models.dto.partCarSale.out.CarViewMakeModelTravelDistDto;
import com.json_xml.parse.models.dto.partCarSale.out.PartViewNamePriceDTO;
import com.json_xml.parse.models.entities.partCarSale.CarEntity;
import com.json_xml.parse.models.entities.partCarSale.PartEntity;
import com.json_xml.parse.repositories.CarRepository;
import com.json_xml.parse.services.CarService;
import com.json_xml.parse.services.PartService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.CAR_JSON_FILEPATH;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final IOUtil ioUtil;
    private final ValidationUtil validation;
    private final Gson gson;
    private final PartService partService;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, IOUtil ioUtil, ValidationUtil validation, Gson gson, PartService partService) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.ioUtil = ioUtil;
        this.validation = validation;
        this.gson = gson;
        this.partService = partService;
    }


    @Override
    public void seedData() throws IOException {
        if (carRepository.count() != 0) {
            ioUtil.print("car_table data is not Empty");
            return;
        }

        ioUtil.print("Data will seed from " + CAR_JSON_FILEPATH + "\nPLEASE WAIT...");
        List<String> report = new ArrayList<>();
        String content = String.join("", ioUtil.readFile(CAR_JSON_FILEPATH));

        Arrays.stream(gson.fromJson(content, CarInputJsonDto[].class))
                .forEach(carIn -> {

                    if (!validation.isValid(carIn)) {
                        report.add("\nInvalid car: " + carIn.getMake() + "\n" +
                                validation.violation(carIn).stream().map(ConstraintViolation::getMessage)
                                        .collect(Collectors.joining(System.lineSeparator())));
                        return;
                    }
                    CarEntity car = modelMapper.map(carIn, CarEntity.class);
                    //Add between 3-5 parts
                    Set<PartEntity> parts = partService.getRandom3To5Parts();
                    car.setParts(parts);
                    carRepository.save(car);
                    report.add("Success add in db car: " + car.getMake());
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
    public String getALLByAndOrderByMake(String make) {

        return gson.toJson(
                carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota")
                        .stream().map(car -> {
                    CarByMakeSortByModelAndTravelDistDto carView =
                            modelMapper.map(car, CarByMakeSortByModelAndTravelDistDto.class);

                    return carView;
                })
                        .collect(Collectors.toList())
        );

    }

    @Override
    public String getAllByCar() {

        return gson.toJson(carRepository.findAll().stream()

                .map(car -> {
                    CarViewCarPartsDto carDto = new CarViewCarPartsDto();
                    carDto.setCar(modelMapper.map(car, CarViewMakeModelTravelDistDto.class));
                    Set<PartViewNamePriceDTO> parts = car.getParts()
                            .stream()
                            .map(p -> modelMapper.map(p, PartViewNamePriceDTO.class))
                            .collect(Collectors.toSet());
                    carDto.setParts(parts);
                    return carDto;
                }).collect(Collectors.toList())
        );
    }
}
