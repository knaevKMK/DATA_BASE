package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constants.Static;
import softuni.exam.domain.dto.json.PlayerJsonDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PictureService pictureService;
    private final TeamService teamService;
    private final PlayerRepository playerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;

    public PlayerServiceImpl(PictureService pictureService, TeamService teamService, PlayerRepository playerRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.pictureService = pictureService;
        this.teamService = teamService;
        this.playerRepository = playerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String importPlayers() throws IOException {
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(this.readPlayersJsonFile(), PlayerJsonDto[].class))
                .forEach(playerDto -> {
                    try {
                        if (!validatorUtil.isValid(playerDto)) {
                            throw new Exception();
                        }
                        Picture picture = pictureService.findByUrl(playerDto.getPicture().getUrl())
                                .orElseThrow(Exception::new);

                        Team team = teamService.findByNameAndPictureUrl(playerDto.getTeam().getName(), playerDto.getTeam().getPicture().getUrl());

                        Player player = modelMapper.map(playerDto, Player.class);
                        player
                                .setPicture(picture)
                                .setTeam(team);

                        playerRepository.save(player);
                        report.add("Successfully imported player - " + player.getFirstName() + " " + player.getLastName());
                    } catch (Exception e) {
                        report.add("Invalid player");
                    }


                });


        return String.join(System.lineSeparator(), report);
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() != 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return fileUtil.readFile(Static.DIR_JSON_PATH + Static.PLAYERS_FILE);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {

        return playerRepository
                .findAllBySalaryIsAfterOrderBySalaryDesc(BigDecimal.valueOf(100000))
                .stream().map(player -> String.format(
                        "Player name: %s %s %nNumber: %d%nSalary: %.2f%nTeam: %s"
                        , player.getFirstName(), player.getLastName()
                        , player.getNumber(), player.getSalary(), player.getTeam().getName()
                ))
                .collect(Collectors.joining(System.lineSeparator()))
                ;
    }

    @Override
    public String exportPlayersInATeam() {
        return "Team: North Hub\n" +
                playerRepository.findAllByTeamName("North Hub")
                        .stream()
                        .map(player ->
                                String.format("Player name: %s %s - %s%nNumber: %d"
                                        , player.getFirstName()
                                        , player.getLastName()
                                        , player.getPosition()
                                        , player.getNumber()))
                        .collect(Collectors.joining(System.lineSeparator()));
    }
}

