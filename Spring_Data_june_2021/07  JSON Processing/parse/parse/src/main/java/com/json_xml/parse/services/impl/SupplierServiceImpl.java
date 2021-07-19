package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partCarSale.input.SupplierInJsonDto;
import com.json_xml.parse.models.dto.partCarSale.out.SuppliersIdNamePartCountDTO;
import com.json_xml.parse.models.entities.partCarSale.SupplierEntity;
import com.json_xml.parse.repositories.SupplierRepository;
import com.json_xml.parse.services.SupplierService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.SUPPLIER_JSON_FILEPATH;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final IOUtil ioUtil;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, IOUtil ioUtil) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
    }

    @Override
    public void seedData() throws IOException {
        if (supplierRepository.count() != 0) {
            ioUtil.print("suppliers_table data is not empty");
            return;
        }

        ioUtil.print("Data will seed from " + SUPPLIER_JSON_FILEPATH + "\nPLEASE WAIT...");

        String content = String.join("", ioUtil.readFile(SUPPLIER_JSON_FILEPATH));

        List<String> report = new ArrayList<>();

        Arrays.stream(gson.fromJson(content, SupplierInJsonDto[].class))
                .forEach(supplierDto -> {
                    if (!validationUtil.isValid(supplierDto)) {
                        report.add("Invalid supplier: " + supplierDto.getName() + "\n" +
                                validationUtil.violation(supplierDto).stream().map(ConstraintViolation::getMessage)
                                        .collect(Collectors.joining(System.lineSeparator())));
                        return;
                    }
                    SupplierEntity supplier = modelMapper.map(supplierDto, SupplierEntity.class);

                    supplierRepository.save(supplier);
                    report.add("Success add suplier: " + supplier.getName());
                });
        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public SupplierEntity getRandomById() {
        return supplierRepository.getById(ThreadLocalRandom.current().nextLong(1, supplierRepository.count() + 1));

    }

    @Override
    public String getLocal() {

        return gson.toJson(supplierRepository.findAllByImporterIsFalse()
                .stream().map(supplier -> {
                    SuppliersIdNamePartCountDTO supplierView =
                            modelMapper.map(supplier, SuppliersIdNamePartCountDTO.class);
                    supplierView.setPartsCount(supplier.getParts().size());
                    return supplierView;
                })
                .collect(Collectors.toList()));
    }
}
