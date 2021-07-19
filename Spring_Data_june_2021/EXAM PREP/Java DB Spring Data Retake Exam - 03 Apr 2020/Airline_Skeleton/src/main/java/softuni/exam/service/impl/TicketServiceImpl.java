package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.input.xml.TicketRootXmlDto;
import softuni.exam.models.entities.PassengerEntity;
import softuni.exam.models.entities.PlaneEntity;
import softuni.exam.models.entities.TicketEntity;
import softuni.exam.models.entities.TownEntity;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.util.IOUtil;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static softuni.exam.constants.Path.*;

@Service
public class TicketServiceImpl implements TicketService {
    private final PassengerService passengerService;
    private final PlaneService planeService;
    private final TownService townService;
    private final TicketRepository ticketRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final IOUtil ioUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public TicketServiceImpl(PassengerService passengerService, PlaneService planeService, TownService townService, TicketRepository ticketRepository, Gson gson, ValidationUtil validationUtil, IOUtil ioUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.passengerService = passengerService;
        this.planeService = planeService;
        this.townService = townService;
        this.ticketRepository = ticketRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() != 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return
                String.join("", ioUtil.readFile(DIR_XML_PATH + TICKET_XML_FILE_PATH));
    }

    @Override
    public String importTickets() throws JAXBException {
        List<String> report = new ArrayList<>();

        TicketRootXmlDto ticketRootXmlDto
                = xmlParser.importXMl(TicketRootXmlDto.class, DIR_XML_PATH + TICKET_XML_FILE_PATH);
        ticketRootXmlDto.getTicketXmlDtoList().forEach(ticketDto -> {
            if (!validationUtil.isValid(ticketDto)) {
                report.add("Invalid ticket");
                return;
            }

            TicketEntity ticket = modelMapper.map(ticketDto, TicketEntity.class);
         //   ticket.setTakeoff(LocalDateTime.parse(ticketDto.getTakeoff(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            TownEntity fromTown = townService.findByName(ticketDto.getFromTown().getName());
            ticket.setFromTown(fromTown);
            TownEntity toTown = townService.findByName(ticketDto.getToTown().getName());
            ticket.setToTown(toTown);
            PassengerEntity passengerEntity = passengerService.findByEmail(ticketDto.getPassenger().getEmail());
            ticket.setPassenger(passengerEntity);
            PlaneEntity plane = planeService.findByRegisterNumber(ticketDto.getPlane().getRegisterNumber());
            ticket.setPlane(plane);
            ticketRepository.save(ticket);
            report.add("Successfully imported Ticket " + ticket.getFromTown().getName() + " - " + ticket.getToTown().getName());

        });


        return String.join(System.lineSeparator(), report);
    }


}
