package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.constatnt.Static;
import softuni.exam.instagraphlite.models.dto.json.input.PictureJsonPathSizeDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.FileUtil;
import softuni.exam.instagraphlite.util.ValidatorUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, FileUtil fileUtil, ModelMapper modelMapper, Gson gson, ValidatorUtil validatorUtil) {
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean areImported() {
        return pictureRepository.count() != 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return fileUtil.content(Static.PICTURE_FILEPATH, "");
    }

    @Override
    public String importPictures() throws IOException {
        List<String> report = new ArrayList<>();

        Arrays.stream(gson.fromJson(this.readFromFileContent(), PictureJsonPathSizeDto[].class))
                .forEach(pictureDto -> {

                    try {
                        if (!validatorUtil.isValid(pictureDto)) {
                            throw new Exception();
                        }

                        Picture picture = modelMapper.map(pictureDto, Picture.class);
                        pictureRepository.save(picture);
                        report.add(String.format(": Successfully imported Picture, with size %s",
                                picture.getSize()));
                    } catch (Exception e) {
                        report.add("Invalid Picture");
                    }
                });
        return String.join(System.lineSeparator(), report);
    }

    @Override
    public String exportPictures() {
        return pictureRepository.findAllBySizeIsGreaterThanOrderBySize(30000.00)
                .stream().map(picture -> String.format("%.2f -%s",
                        picture.getSize(), picture.getPath()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Optional<Picture> getPictureByPath(String profilePicture) {
        return pictureRepository.findByPath(profilePicture);
    }
}
