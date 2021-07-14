package com.json.json.services.impl;

import com.google.gson.Gson;
import com.json.json.models.dto.UserDTO;
import com.json.json.models.entites.UserEntity;
import com.json.json.repositories.UserRepository;
import com.json.json.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, Gson gson, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedDataFromJson() throws IOException {
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/files/users.json")));
        Arrays.stream(gson.fromJson(content, UserDTO[].class))
                .forEach(userDTO -> {
                    System.out.println(userDTO.toString());
                    try {
                        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
                        userRepository.save(userEntity);
                        System.out.println("Successful create "+userEntity.toString()+" in DB");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
    }
}
