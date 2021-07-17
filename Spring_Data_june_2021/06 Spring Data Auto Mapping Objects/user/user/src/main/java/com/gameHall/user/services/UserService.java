package com.gameHall.user.services;

import com.gameHall.user.models.dto.insert.UserLoginDto;
import com.gameHall.user.models.dto.insert.UserRegisterDto;
import com.gameHall.user.models.entities.UserEntity;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logout();

    Long getIdOnLogedUser();

    UserEntity isLoged();
}
