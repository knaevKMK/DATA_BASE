package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.CardDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.EmployeeCardService;
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
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final EmployeeCardRepository cardRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public EmployeeCardServiceImpl(EmployeeCardRepository cardRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.cardRepository = cardRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return cardRepository.count() != 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return String.join("", fileUtil.readFile(DIR_PATH_TO_FILES + "employee-cards.json"));
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(this.readEmployeeCardsJsonFile(), CardDto[].class))
                .forEach(cardDto -> {
                    try {
                        if (!validationUtil.isValid(cardDto)) {
                            throw new Exception();
                        }
                        EmployeeCard card = modelMapper.map(cardDto, EmployeeCard.class);

                        cardRepository.save(card);
                        report.add(String.format(SUCCESSFUL_IMPORT_MESSAGE, card.getClass().getSimpleName(),
                                card.getNumber()));
                    } catch (Exception e) {
                        report.add(INCORRECT_DATA_MESSAGE);
                    }
                });
        return String.join(System.lineSeparator(), report);
    }
}
