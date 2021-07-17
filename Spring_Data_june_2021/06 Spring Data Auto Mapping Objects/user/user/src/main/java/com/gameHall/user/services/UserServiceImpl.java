package com.gameHall.user.services;

import com.gameHall.user.models.dto.insert.UserLoginDto;
import com.gameHall.user.models.dto.insert.UserRegisterDto;
import com.gameHall.user.models.entities.UserEntity;
import com.gameHall.user.models.entities.UserRole;
import com.gameHall.user.repositories.UserRepository;
import com.gameHall.user.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    private UserEntity logedUser;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationUtil validation, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validation = validation;
        this.modelMapper = modelMapper;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            return ("User with this email exist");
        }
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return ("Wrong confirm password");
        }
        if (!validation.isValid(userRegisterDto)) {
            return (validation.violation(userRegisterDto)
                    .stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator())));
        }

        UserEntity userEntity = modelMapper.map(userRegisterDto, UserEntity.class);
        if (userRepository.count() == 0) {
            userEntity.setRole(UserRole.ADMINISTRATOR);
        } else {
            userEntity.setRole(UserRole.USER);
        }
        userRepository.save(userEntity);
        return userEntity.getFullName() + " was registered";
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        if (logedUser != null) {

            if (logedUser.getEmail().equals(userLoginDto.getEmail())
                    && logedUser.getPassword().equals(userLoginDto.getPassword())
            ) {
                return "\nYou already loged";
            }

            return ("\nPlease logout before, login!\nYou have logged user: " + logedUser.getFullName());
        }
        if (!validation.isValid(userLoginDto)) {
            return (validation.violation(userLoginDto)
                    .stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        logedUser = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword()).orElse(null);
        if (logedUser == null) {
            return ("Incorrect username / password");
        }
        return "Successfully logged in Ivan";
    }

    @Override
    public String logout() {
        if (logedUser == null) {
            return ("Cannot log out. No user was logged in.");
        }
        String fullName = logedUser.getFullName();
        logedUser = null;
        return "User " + fullName + " successfully logged out";
    }

    @Override
    public Long getIdOnLogedUser() {
        if (logedUser == null) {
            return null;
        }
        return logedUser.getId();
    }

    @Override
    public UserEntity isLoged() {
        return logedUser;
    }
}
