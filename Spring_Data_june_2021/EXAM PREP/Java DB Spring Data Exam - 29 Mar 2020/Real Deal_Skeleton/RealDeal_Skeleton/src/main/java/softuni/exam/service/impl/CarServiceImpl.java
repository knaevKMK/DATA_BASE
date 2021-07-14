package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.DTO.CarDTO;
import softuni.exam.models.entities.CarEntity;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static softuni.exam.constatnt.Static.CAR_FILEPATH;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() != 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {

        return String.join(System.lineSeparator()
                , Files.readAllLines(Path.of(CAR_FILEPATH)));

    }

    @Override
    public String importCars() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of(CAR_FILEPATH)));
        List<String> result = new ArrayList<>();
        Arrays.stream(gson.fromJson(content, CarDTO[].class))
                .forEach(carDTO -> {
                    try {
                        CarEntity carEntity = modelMapper.map(carDTO, CarEntity.class);
                        carRepository.save(carEntity);
                        result.add(String.format("Successfully imported car - %s - %s"
                                ,carEntity.getMake(),carEntity.getModel()));
                    } catch (Exception e) {
                        result.add("Invalid car");
                    }
                });
        ;

        return String.join(System.lineSeparator(),result);
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }
}
