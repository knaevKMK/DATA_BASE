package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.constants.Paths;
import com.json_xml.parse.models.dto.input.UserInFromFileJsonDto;
import com.json_xml.parse.models.entities.UserEntity;
import com.json_xml.parse.repositories.UserRepository;
import com.json_xml.parse.services.UserService;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validator, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public void seedData() throws IOException {
        if (userRepository.count() != 0) {
            return;
        }

        String content = String.join("", Files.readAllLines(Path.of(Paths.USER_JSON_FILEPATH)));
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(content, UserInFromFileJsonDto[].class))
                .forEach(userDto -> {

                    try {
                        if (!validator.isValid(userDto)) {
                            throw new Exception(validator.violation(userDto).stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(System.lineSeparator())));
                        }

                        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
                        userRepository.save(userEntity);
                        report.add("Successfully imported:" + userEntity.getLastName());

                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }
                });
        System.out.println(String.join(System.lineSeparator(), report));

    }
}
