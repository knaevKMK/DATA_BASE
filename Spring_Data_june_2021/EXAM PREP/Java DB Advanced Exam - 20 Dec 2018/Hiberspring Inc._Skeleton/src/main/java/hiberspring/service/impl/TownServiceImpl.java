package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.TownNamePopulationDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hiberspring.common.Constants.*;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return townRepository.count() != 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return String.join("", fileUtil.readFile(DIR_PATH_TO_FILES + "towns.json"));
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(this.readTownsJsonFile(), TownNamePopulationDto[].class))
                .forEach(townDto -> {

                    try {
                        if (!validationUtil.isValid(townDto)) {
                            throw new Exception();
                        }
                        Town town = modelMapper.map(townDto, Town.class);
                        townRepository.save(town);
                        report.add(String.format(SUCCESSFUL_IMPORT_MESSAGE,town.getClass().getSimpleName(),
                                town.getName()));
                    } catch (Exception e) {
                        report.add(INCORRECT_DATA_MESSAGE);
                    }

                });
        return String.join(System.lineSeparator(), report);
    }
}
