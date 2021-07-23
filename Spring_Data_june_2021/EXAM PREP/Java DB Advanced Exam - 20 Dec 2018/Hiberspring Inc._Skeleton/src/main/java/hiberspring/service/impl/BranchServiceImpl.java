package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.BranchNameTownNameDto;
import hiberspring.domain.dtos.TownNamePopulationDto;
import hiberspring.domain.entities.BranchEntity;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hiberspring.common.Constants.*;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return String.join("", fileUtil.readFile(DIR_PATH_TO_FILES + "branches.json"));
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(this.readBranchesJsonFile(), BranchNameTownNameDto[].class))
                .forEach(branchDto -> {

                    try {
                        if (!validationUtil.isValid(branchDto)) {
                            throw new Exception();
                        }
                        Town town = townRepository.findByName(branchDto.getTown())
                                .orElseThrow(() -> new Exception());
                        BranchEntity branchEntity =
//                                modelMapper.map(branchDto, BranchEntity.class);
new BranchEntity();
                        branchEntity.setName(branchDto.getName());
                        branchEntity.setTown(town);
                        branchRepository.save(branchEntity);
                        report.add(String.format(SUCCESSFUL_IMPORT_MESSAGE, branchEntity.getClass().getSimpleName(),
                                branchEntity.getName()));
                    } catch (Exception e) {
                        report.add(INCORRECT_DATA_MESSAGE);
                    }

                });
        return String.join(System.lineSeparator(), report);
    }
}
