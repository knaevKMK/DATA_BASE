package com.json.part2.services.impl;

import com.google.gson.Gson;
import com.json.part2.models.importDto.SupplierSeedDTO;
import com.json.part2.models.entities.SupplierEntity;
import com.json.part2.repositories.SupplierRepository;
import com.json.part2.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public String seedData() throws IOException {
        if (supplierRepository.count()!=0){
            return "Suppliers exist in your data";
        }
        String content = String.join("", Files.readAllLines(Path.of("src/main/resources/files/suppliers.json")));
        List<String> report = new ArrayList<>();
        Arrays.stream(gson.fromJson(content, SupplierSeedDTO[].class))
                .forEach(supplierSeedDTO -> {
                    try {
                        SupplierEntity supplierEntity = modelMapper.map(supplierSeedDTO, SupplierEntity.class);
                        supplierRepository.save(supplierEntity);
                        report.add("Success add supplier: " + supplierEntity.getName());
                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }

                });

        return String.join(System.lineSeparator(), report);
    }
}
