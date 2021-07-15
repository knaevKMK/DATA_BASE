package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constatnt.Static;
import softuni.exam.models.dto.PictureDTO;
import softuni.exam.models.entities.PictureEntity;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.util.DateFormatAdapter;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {


    private final PictureRepository pictureRepository;
    private final CarService carService;

    private final Gson gson;
    private final FileUtil fileUtil;
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    private final DateFormatAdapter dateFormatAdapter;

    public PictureServiceImpl(PictureRepository pictureRepository, CarService carService, Gson gson, FileUtil fileUtil, ValidationUtil validation, ModelMapper modelMapper, DateFormatAdapter dateFormatAdapter) {
        this.pictureRepository = pictureRepository;
        this.carService = carService;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.validation = validation;
        this.modelMapper = modelMapper;
        this.dateFormatAdapter = dateFormatAdapter;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() != 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return fileUtil.content(Static.PICTURE_FILEPATH, System.lineSeparator());
    }

    @Override
    public String importPictures() throws IOException {
        List<String> result = new ArrayList<>();
        Arrays.stream(gson.fromJson(readPicturesFromFile(), PictureDTO[].class))
                .forEach(pictureDTO -> {
                    try {
                        if (!this.validation.isValid(pictureDTO)
                                || this.pictureRepository.findByName(pictureDTO.getName()).isPresent()
                        ) {
                            throw new Exception();
                        }
                        PictureEntity pictureEntity = modelMapper.map(pictureDTO, PictureEntity.class);

                        pictureEntity.setCar(this.carService.findCarById(pictureDTO.getCar()));

                        pictureRepository.save(pictureEntity);

                        result.add("Successfully import picture - " + pictureEntity.getName());

                    } catch (Exception e) {
                        result.add("Invalid picture");
                    }
                });

        return String.join(System.lineSeparator(), result);
    }
}
