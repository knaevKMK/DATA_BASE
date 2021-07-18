package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partCarSale.input.PartInJsonDto;
import com.json_xml.parse.models.entities.partCarSale.PartEntity;
import com.json_xml.parse.models.entities.partCarSale.SupplierEntity;
import com.json_xml.parse.repositories.PartRepository;
import com.json_xml.parse.services.PartService;
import com.json_xml.parse.services.SupplierService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.PART_JSON_FILEPATH;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final IOUtil ioUtil;
    private final ValidationUtil validation;
    private final Gson gson;
    private final SupplierService supplierService;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, IOUtil ioUtil, ValidationUtil validation, Gson gson, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.ioUtil = ioUtil;
        this.validation = validation;
        this.gson = gson;
        this.supplierService = supplierService;
    }

    @Override
    public void seedData() throws IOException {
        if (partRepository.count() != 0) {
            ioUtil.print("part_table data is not empty");
            return;
        }

        List<String> report = new ArrayList<>();
        ioUtil.print("Data will seed from " + PART_JSON_FILEPATH + "\nPLEASE WAIT...");
        String contetn = String.join("", ioUtil.readFile(PART_JSON_FILEPATH));
        Arrays.stream(gson.fromJson(contetn, PartInJsonDto[].class))
                .forEach(parDTO -> {
                    if (!validation.isValid(parDTO)) {
                        report.add("Invalid part: " + parDTO.getName() + "\n" +
                                validation.violation(parDTO).stream().map(ConstraintViolation::getMessage)
                                        .collect(Collectors.joining(System.lineSeparator())));
                        return;
                    }
                    PartEntity part = modelMapper.map(parDTO, PartEntity.class);

                    SupplierEntity supplier = supplierService.getRandomById();
                    part.setSupplier(supplier);

                    partRepository.save(part);
                    report.add("Success add part: " + part.getName());
                });
        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public Set<PartEntity> getRandom3To5Parts() {
        Set<PartEntity> parts = new HashSet<>();

        int count = ThreadLocalRandom.current().nextInt(3, 5);
        long upBound = partRepository.count() + 1;
        for (int i = 1; i <= count; i++) {
            long index = ThreadLocalRandom.current().nextLong(upBound) + 1;
            PartEntity part = partRepository.findById(index).orElse(null);
                 if (part==null) {
                i--;
                continue;
            }
            parts.add(part);
        }
        return parts;
    }


}
