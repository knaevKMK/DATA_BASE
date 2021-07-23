package hiberspring.service.impl;

import hiberspring.domain.dtos.EmployeeRootDto;
import hiberspring.domain.entities.BranchEntity;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.domain.entities.EmployeeEntity;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import hiberspring.service.EmployeeService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static hiberspring.common.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final BranchRepository branchRepository;
    private final EmployeeCardRepository cardRepository;
    private final EmployeeRepository employeeRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public EmployeeServiceImpl(BranchRepository branchRepository, EmployeeCardRepository cardRepository, EmployeeRepository employeeRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.cardRepository = cardRepository;
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean employeesAreImported() {
        return employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return String.join("", fileUtil.readFile(DIR_PATH_TO_FILES + "employees.xml"));
    }

    @Override
    public String importEmployees() throws JAXBException {
        List<String> report = new ArrayList<>();


        xmlParser.parseXml(EmployeeRootDto.class, "src/main/resources/files/employees.xml")
                .getEmployeeDtos()
                .forEach(employeeDto -> {
                    try {
                        BranchEntity branch = branchRepository.findByName(employeeDto.getBranchName())
                                .orElseThrow(() -> new Exception());
                        EmployeeCard card = cardRepository.findByNumber(employeeDto.getCardNumber())
                                .orElseThrow(() -> new Exception());
                        if (employeeRepository.findByCardNumber(employeeDto.getCardNumber())!=null)
                            throw new Exception();
                        if (!validationUtil.isValid(employeeDto)) {
                            throw new Exception();
                        }
                        EmployeeEntity employee = modelMapper.map(employeeDto, EmployeeEntity.class);
                        employee.setCard(card);
                        employee.setBranch(branch);

                        employeeRepository.save(employee);

                        report.add(String.format(SUCCESSFUL_IMPORT_MESSAGE, employee.getClass().getSimpleName(),
                                employee.getFirstName() + " " + employee.getLastName()));
                    } catch (Exception e) {
                        report.add(INCORRECT_DATA_MESSAGE);
                    }
                });


        return String.join(System.lineSeparator(), report);
    }

    @Override
    public String exportProductiveEmployees() {

        return employeeRepository.findAllByOrderByFullNameAscThanByPositionLengthDesc()
                .stream().map(empl ->
                        String.format(
                                "Name: %s %s%nPosition: %s%nCard Number: %s%n%s"
                                , empl.getFirstName()
                                , empl.getLastName()
                                , empl.getPosition()
                                , empl.getCard().getNumber()
                                ,"-------------------------"
                        ))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
