package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constatnt.Static;
import softuni.exam.models.DTO.PictureDTO;
import softuni.exam.models.entities.PictureEntity;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {


    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() != 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return String.join(System.lineSeparator()
                , Files.readAllLines(Path.of("src/main/resources/files/json/pictures.json")));
    }

    @Override
    public String importPictures() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of(Static.PICTURE_FILEPATH)));
        List<String> result = new ArrayList<>();
        Arrays.stream(gson.fromJson(content, PictureDTO[].class))
                .forEach(pictureDTO -> {
                    try {
                        PictureEntity pictureEntity = modelMapper.map(pictureDTO, PictureEntity.class);
                        pictureRepository.save(pictureEntity);
                        result.add("Successfully import picture - "+ pictureEntity.getName());
                    } catch (Exception e) {
                        result.add("Invalid picture");
                    }
                });

        return String.join(System.lineSeparator(), result);
    }
}
