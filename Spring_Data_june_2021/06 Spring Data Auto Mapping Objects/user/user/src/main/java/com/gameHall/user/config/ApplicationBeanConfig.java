package com.gameHall.user.config;

import com.gameHall.user.models.dto.insert.GameInsertDto;
import com.gameHall.user.models.entities.GameEntity;
import com.gameHall.user.util.ValidationUtil;
import com.gameHall.user.util.ValidationUtilImpl;
import com.gameHall.user.util.IoUtil;
import com.gameHall.user.util.IoUtilImpl;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper
                .typeMap(GameInsertDto.class, GameEntity.class)
                .addMappings(mapper ->
                        mapper.map(GameInsertDto::getReleaseDate,GameEntity::setReleaseDate));
        Converter<String, LocalDate> localDateConverter =
                new Converter<String, LocalDate>() {
                    @Override
                    public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                        return mappingContext.getSource() == null
                                ? LocalDate.now()
                                : LocalDate.parse(mappingContext.getSource(),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    }
                };
        modelMapper.addConverter(localDateConverter);
        return modelMapper;
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl(validator());
    }


    @Bean
    public IoUtil ioUtil() {
        return new IoUtilImpl();
    }
}
