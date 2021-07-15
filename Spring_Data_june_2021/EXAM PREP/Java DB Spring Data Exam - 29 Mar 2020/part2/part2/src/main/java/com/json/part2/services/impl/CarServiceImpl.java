package com.json.part2.services.impl;

import com.google.gson.Gson;
import com.json.part2.models.importDto.CarSeedDTO;
import com.json.part2.models.entities.CarEntity;
import com.json.part2.repositories.CarRepository;
import com.json.part2.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public String seedData() throws IOException {
        if (carRepository.count() != 0) {
            return "Data all ready exist";
        }
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/files/cars.json")));
        List<String> report = new ArrayList<>();

        Arrays.stream(gson.fromJson(content, CarSeedDTO[].class))
                .forEach(carSeedDTO -> {
                    try {
                        CarEntity carEntity = modelMapper.map(carSeedDTO, CarEntity.class);
                        carRepository.save(carEntity);
                        report.add("Success add car: " + carEntity.getMake() + " model: " + carEntity.getModel());
                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }
                });
        return String.join(System.lineSeparator(), report);
    }
}
