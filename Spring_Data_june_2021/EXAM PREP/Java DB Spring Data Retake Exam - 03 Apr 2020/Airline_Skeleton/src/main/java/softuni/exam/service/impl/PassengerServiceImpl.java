package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.input.PassengerDto;
import softuni.exam.models.entities.PassengerEntity;
import softuni.exam.models.entities.TownEntity;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.IOUtil;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.constants.Path.DIR_JSON_PATH;
import static softuni.exam.constants.Path.PASSENGER_JSON_FILE_PATH;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final IOUtil ioUtil;
    private final ModelMapper modelMapper;
    private final TownService townService;

    public PassengerServiceImpl(PassengerRepository passengerRepository, Gson gson, ValidationUtil validationUtil, IOUtil ioUtil, ModelMapper modelMapper, TownService townService) {
        this.passengerRepository = passengerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
        this.modelMapper = modelMapper;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() != 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return String.join("", ioUtil.readFile(DIR_JSON_PATH + PASSENGER_JSON_FILE_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(this.readPassengersFileContent(), PassengerDto[].class))
                .forEach(passengerDto -> {

                    if (!validationUtil.isValid(passengerDto)) {
                        report.add("Invalid Passenger");
                        return;
                    }
                    PassengerEntity passenger = modelMapper.map(passengerDto, PassengerEntity.class);
                    TownEntity townEntity = townService.findByName(passengerDto.getTown());
                    passenger.setTown(townEntity);
                    passengerRepository.save(passenger);
                    report.add("Successfully imported Passenger " + passenger.getLastName() + " - " + passenger.getEmail());
                });
        return String.join(System.lineSeparator(), report);
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {

        return passengerRepository.getAllOrderByCountTicketDescThenByEmailAsc()
                .stream().map(e ->
                        String.format("Passenger %s  %s\n" +
                                        "\tEmail - %s\n" +
                                        "\tPhone - %s\n" +
                                        "\tNumber of tickets - %d\n"
                                , e.getFirstName(), e.getLastName()
                                , e.getEmail(), e.getPhoneNumber(), e.getTickets().size()))
                .collect(Collectors.joining(System.lineSeparator()));


    }

    @Override
    public PassengerEntity findByEmail(String email) {


        return passengerRepository.findByEmail(email);
    }
}
