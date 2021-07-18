package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.constants.Paths;
import com.json_xml.parse.models.dto.partCarSale.input.CustomerInJsonDto;
import com.json_xml.parse.models.dto.partCarSale.out.CustomersOrderByBirthDateDescDTO;
import com.json_xml.parse.models.entities.partCarSale.CustomerEntity;
import com.json_xml.parse.models.entities.partCarSale.SaleEntity;
import com.json_xml.parse.repositories.CustomerRepository;
import com.json_xml.parse.services.CustomerService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.*;
import static com.json_xml.parse.constants.Paths.CATEGORY_JSON_FILEPATH;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final IOUtil ioUtil;
    private final ValidationUtil validation;
    private final Gson gson;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, IOUtil ioUtil, ValidationUtil validation, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.ioUtil = ioUtil;
        this.validation = validation;
        this.gson = gson;
    }


    @Override
    public void seedData() throws IOException {
        if (customerRepository.count() != 0) {
            ioUtil.print("customers_table data is not empty");
            return;
        }
        List<String> report = new ArrayList<>();
        ioUtil.print("Data will seed from " + CUSTOMER_JSON_FILEPATH + "\nPLEASE WAIT...");

        String content = String.join("", ioUtil.readFile(CUSTOMER_JSON_FILEPATH));

        Arrays.stream(gson.fromJson(content, CustomerInJsonDto[].class))
                .forEach(customerDto -> {
                    if (!validation.isValid(customerDto)) {
                        report.add("Invalid customer: " + customerDto.getName() + "\n"
                                + String.join(System.lineSeparator(), (CharSequence) validation.violation(customerDto)
                                .stream().map(ConstraintViolation::getMessage)));
                        return;
                    }
                    CustomerEntity customer = modelMapper.map(customerDto, CustomerEntity.class);

                    customerRepository.save(customer);
                    report.add("Success add customer: " + customer.getName());
                });
        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public CustomerEntity getRandomById() {
        long id = ThreadLocalRandom.current().nextLong(customerRepository.count()) + 1;
        CustomerEntity customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            customer = getRandomById();
        }
        return customer;
    }

    @Override
    public String getALLOrderByBirthDateAsc() {

//repository.findAll(Sort.by(Sort.Direction.DESC, "colName"))
        return gson.toJson(customerRepository.findAll(Sort.by(Sort.Direction.ASC, "birthDate"))
                .stream().map(customer -> {
                    CustomersOrderByBirthDateDescDTO customerView =
                            modelMapper.map(customer, CustomersOrderByBirthDateDescDTO.class);
                    customerView.setBirthDate(customer.getBirthDate().toString());
                    customerView.setSales(new HashSet<SaleEntity>());
                    return customerView;
                })
                .collect(Collectors.toList()));
    }
}
