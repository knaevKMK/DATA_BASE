package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarSeedDto;
import softuni.exam.models.entities.CarEntity;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.DateFormatAdapter;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constatnt.Static.CAR_FILEPATH;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final DateFormatAdapter dateFormatAdapter;

    public CarServiceImpl(CarRepository carRepository,
                          FileUtil fileUtil,
                          Gson gson,
                          ModelMapper modelMapper, ValidationUtil validator, DateFormatAdapter dateFormatAdapter) {
        this.carRepository = carRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.dateFormatAdapter = dateFormatAdapter;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() != 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {

        return fileUtil.content(CAR_FILEPATH, System.lineSeparator());

    }

    @Override
    public String importCars() throws IOException {
        List<String> result = new ArrayList<>();
        Arrays.stream(gson.fromJson(fileUtil.content(CAR_FILEPATH, System.lineSeparator()), CarSeedDto[].class))
                .forEach(carSeedDto -> {

                    try {
                        if (!this.validator.isValid(carSeedDto)) {
                            throw new Exception();
                        }
                        CarEntity carEntity = modelMapper.map(carSeedDto, CarEntity.class);
                        carEntity.setRegisteredOn(dateFormatAdapter.toLocalDate(carSeedDto.getRegisteredOn(), ("dd/MM/yyyy")));
                        carRepository.save(carEntity);

                        result.add(String.format("Successfully imported car - %s - %s"
                                , carEntity.getMake(), carEntity.getModel()));
                    } catch (Exception e) {
                        result.add("Invalid car");
                    }
                });
        ;

        return String.join(System.lineSeparator(), result);
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }

    @Override
    public CarEntity findCarById(int id) {
        return this.carRepository.findById(id).get();
    }
}
