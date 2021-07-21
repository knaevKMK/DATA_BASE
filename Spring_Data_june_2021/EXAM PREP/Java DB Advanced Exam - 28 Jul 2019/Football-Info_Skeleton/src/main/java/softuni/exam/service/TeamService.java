package softuni.exam.service;

import softuni.exam.domain.entities.Team;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

public interface TeamService {

    String importTeams() throws JAXBException;

    boolean areImported();

    String readTeamsXmlFile() throws IOException;

    Team findByNameAndPictureUrl(String name, String pictureUrl) throws Exception;
}
