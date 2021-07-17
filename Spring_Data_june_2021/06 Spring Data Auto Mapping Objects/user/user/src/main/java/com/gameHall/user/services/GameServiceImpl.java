package com.gameHall.user.services;

import com.gameHall.user.models.dto.insert.GameEditDto;
import com.gameHall.user.models.dto.insert.GameInsertDto;
import com.gameHall.user.models.dto.views.GameDetailsDto;
import com.gameHall.user.models.dto.views.GameViewDto;
import com.gameHall.user.models.entities.GameEntity;
import com.gameHall.user.models.entities.UserEntity;
import com.gameHall.user.models.entities.UserRole;
import com.gameHall.user.repositories.GameRepository;
import com.gameHall.user.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validation;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validation, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validation = validation;
        this.userService = userService;
    }

    @Override
    public String addGame(GameInsertDto gameInsertDto) {
        if (!isLogedAdmin()) {

            return ("You must login as Administrator before add game");
        }
        GameEntity gameEntity = this.gameRepository.findByTitle(gameInsertDto.getTitle())
                .orElse(null);

        if (gameEntity != null) {
            return ("Game with title: " + gameEntity.getTitle() + " exist");
        }
        if (!validation.isValid(gameInsertDto)) {
            return (validation.violation(gameInsertDto).stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        gameEntity = modelMapper.map(gameInsertDto, GameEntity.class);
        gameRepository.save(gameEntity);
        return "Added " + gameEntity.getTitle();
    }

    private boolean isLogedAdmin() {
        UserEntity currentUser = userService.isLoged();
        return currentUser != null && currentUser.getRole().equals(UserRole.ADMINISTRATOR);
    }

    @Override
    public String editGame(GameEditDto gameEditDto) throws IllegalAccessException {
        //is admin
        if (!isLogedAdmin()) {
            return ("You must login as Administrator before edit game");
        }

        //if exist same game
        GameEntity gameEntity = (isExistById(gameEditDto.getId()))
                .orElse(null);
        if (gameEntity == null) {
            return ("Does not exist game with this id");
        }

        // validate fields
        if (!validation.isValid(gameEditDto)) {
            return (validation.violation(gameEditDto).stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        //create new Entity via map and set of genuine Entity only NOT null values of the fields(from Mapped Entity)
        //  with reflection
        GameEntity gameEntityEdit = modelMapper.map(gameEditDto, GameEntity.class);

        Field[] fields = gameEntityEdit.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(gameEntityEdit) == null) {
                continue;
            }
            field.set(gameEntity, field.get(gameEntityEdit));
        }
        System.out.println(gameEntity.toString());

        gameRepository.save(gameEntity);
        return "Edited  " + gameEntity.getTitle();
    }

    @Override
    public String delete(long id) {
        if (!isLogedAdmin()) {
            return ("You must login as Administrator before delete game");
        }
        GameEntity gameEntity = (isExistById(id)).orElse(null);
        if (gameEntity == null) {
            return ("Does not exist game with this id");
        }

        gameRepository.delete(gameEntity);
        return "Deleted " + gameEntity.getTitle();
    }

    @Override
    public String showAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(g -> modelMapper.map(g, GameViewDto.class))
                .map(g -> String.format("%s %s", g.getTitle(), g.getPrice()))
                .collect(Collectors.joining(System.lineSeparator()));


    }

    @Override
    public String detailsOfGameByName(String name) {
        GameEntity gameEntity = gameRepository.findByTitle(name).orElse(null);
        if (gameEntity == null) {
            return ("\nDoes not exist game with title: " + name);
        }
        GameDetailsDto gameDetailsDto = modelMapper.map(gameEntity, GameDetailsDto.class);
        return gameDetailsDto.toString();
    }

    @Override
    public String getOwnGames() {
        Long userId = userService.getIdOnLogedUser();
        String result = gameRepository.findAllByUsersId(userId).stream().map(GameEntity::getTitle).collect(Collectors.joining(System.lineSeparator()));
        return result.isBlank() ? "You have not games" : result;
    }

    @Override
    public Optional<GameEntity> findGameByTitle(String gameName) {
        return gameRepository.findByTitle(gameName);
    }

    private Optional<GameEntity> isExistById(Long id) {
        return gameRepository.findById(id);

    }
}
