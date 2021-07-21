package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.xml.TeamRootXmlDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.Static.*;

@Service
public class TeamServiceImpl implements TeamService {
    private final PictureService pictureService;
    private final TeamRepository teamRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;

    public TeamServiceImpl(PictureService pictureService, TeamRepository teamRepository,
                           XmlParser xmlParser, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.pictureService = pictureService;
        this.teamRepository = teamRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String importTeams() throws JAXBException {
        List<String> report = new ArrayList<>();
        xmlParser.importXMl(TeamRootXmlDto.class, DIR_XML_PATH + TEAMS_FILE)
                .getTeamXmlNameDtos()
                .forEach(teamDto -> {

                    try {
                        if (!validatorUtil.isValid(teamDto)) {
                            throw new Exception();
                        }
                        Team team = modelMapper.map(teamDto, Team.class);
                        Picture picture = pictureService.findByUrl(teamDto.getPicture().getUrl())
                                .orElseThrow(Exception::new);
                        team.setPicture(picture);
                        teamRepository.save(team);
                        report.add("Successfully imported picture - " + team.getName());
                    } catch (Exception e) {
                        report.add("Invalid team");
                    }
                });


        return String.join(System.lineSeparator(), report);
    }

    @Override
    public boolean areImported() {

        return teamRepository.count() != 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {

        return fileUtil.readFile(DIR_XML_PATH + TEAMS_FILE);
    }

    @Override
    public Team findByNameAndPictureUrl(String name, String pictureUrl) throws Exception {

        Picture picture = pictureService.findByUrl(pictureUrl)
                .orElseThrow(Exception::new);
        //Todo pictureUrl==team.getPicture().getUrl();
        return teamRepository.findByName(name).orElseThrow(Exception::new);
    }
}
