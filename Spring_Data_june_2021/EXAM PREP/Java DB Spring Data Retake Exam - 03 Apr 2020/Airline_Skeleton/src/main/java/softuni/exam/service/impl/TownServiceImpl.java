package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.input.TownJsonDto;
import softuni.exam.models.entities.TownEntity;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.IOUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static softuni.exam.constants.Path.DIR_JSON_PATH;
import static softuni.exam.constants.Path.TOWN_JSON_FILE_PATH;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final IOUtil ioUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, IOUtil ioUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return townRepository.count() != 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return
                String.join("", ioUtil.readFile(DIR_JSON_PATH + TOWN_JSON_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(this.readTownsFileContent(), TownJsonDto[].class))
                .forEach(townDto -> {

                    if (!validationUtil.isValid(townDto)) {
                        report.add("Invalid Town");
                        return;
                    }
                    TownEntity town = modelMapper.map(townDto, TownEntity.class);
                    townRepository.save(town);
                    report.add("Successfully imported Town " + town.getName() + " - " + town.getPopulation());
                });

        return String.join(System.lineSeparator(), report);
    }

    @Override
    public TownEntity findByName(String town) {
        return townRepository.findByName(town);
    }
}
