package com.gameHall.user.services;

import com.gameHall.user.models.dto.insert.GameEditDto;
import com.gameHall.user.models.dto.insert.GameInsertDto;
import com.gameHall.user.models.entities.GameEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public interface GameService {
    String addGame(GameInsertDto gameInsertDto);

    String editGame(GameEditDto gameEditDto) throws InvocationTargetException, IllegalAccessException;

    String delete(long id);

    String showAllGames();

    String detailsOfGameByName(String name);

    String getOwnGames();

    Optional<GameEntity> findGameByTitle(String gameName);
}
