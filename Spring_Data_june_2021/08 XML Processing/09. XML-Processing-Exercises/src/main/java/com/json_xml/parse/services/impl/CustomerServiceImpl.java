package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.dto.partCarSale.out.*;
import com.json_xml.parse.models.entities.partCarSale.CustomerEntity;
import com.json_xml.parse.models.entities.partCarSale.PartEntity;
import com.json_xml.parse.models.entities.partCarSale.SaleEntity;
import com.json_xml.parse.repositories.CustomerRepository;
import com.json_xml.parse.services.CustomerService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import com.json_xml.parse.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.*;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final IOUtil ioUtil;
    private final ValidationUtil validation;
    private final XmlParser xmlParser;


    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, IOUtil ioUtil, ValidationUtil validation, Gson gson, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.ioUtil = ioUtil;
        this.validation = validation;
        this.xmlParser = xmlParser;
    }


    @Override
    public void seedData() throws IOException, JAXBException {
        if (customerRepository.count() != 0) {
            ioUtil.print("customers_table data is not empty");
            return;
        }
        List<String> report = new ArrayList<>();
        ioUtil.print("Data will seed from " + CUSTOMER_XML_FILEPATH + "\nPLEASE WAIT...");

        String content = String.join("", ioUtil.readFile(CUSTOMER_XML_FILEPATH));
        xmlParser.importXMl(CustomerRootDto.class, CUSTOMER_XML_FILEPATH)
                .getCustomers()
                .forEach(customerDto -> {
                    try {


                        if (!validation.isValid(customerDto)) {
                            throw new Exception("Invalid customer: " + customerDto.getName() + "\n"
                                    + String.join(System.lineSeparator(), (CharSequence) validation.violation(customerDto)
                                    .stream().map(ConstraintViolation::getMessage)));

                        }
                        CustomerEntity customer = modelMapper.map(customerDto, CustomerEntity.class);

                        customerRepository.save(customer);
                        report.add("Success add customer: " + customer.getName());
                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }
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
    public void getALLOrderByBirthDateAsc() throws JAXBException {
//                getALLOrderByBirthDateAsc
        CustomerRootDto customerRootDto = new CustomerRootDto();
        customerRootDto.setCustomers(customerRepository
                .findAll(Sort.by(Sort.Direction.ASC, "birthDate"))
                .stream().map(customer -> {
                    CustomerIdNameBirhDateYongerDto customerView =
                            modelMapper.map(customer, CustomerIdNameBirhDateYongerDto.class);
//                    customerView.setBirthDate(customer.getBirthDate().toString());
                    return customerView;
                })
                .collect(Collectors.toSet()));
        xmlParser.exportXML(customerRootDto, OUT_DIR_XML_FILEPATH + OUT_ORDERED_CUSTOMER_FILE);
    }

    @Override
    public void getCustomers() throws JAXBException {
        CustomerRoot2Dto customerRoot2Dto = new CustomerRoot2Dto();
        Set<CustomerEntity> allBySalesIsNotNull = customerRepository.findAllBySalesIsNotNull();
        customerRoot2Dto.setCustmoerDtos(allBySalesIsNotNull
                .stream()
                .map(customer -> {
                    CustomerFullNameBoughtCarsSpentMoneyDto customerDto = new CustomerFullNameBoughtCarsSpentMoneyDto();
                    Set<SaleEntity> sales = customer.getSales();
                    customerDto.setFullName(customer.getName())
                            .setCountCars(sales.size());

                    customerDto.setSpentMoney(BigDecimal.valueOf(sales.stream().mapToDouble(sale -> {
                        BigDecimal discount = sale.getDiscount();
                        Set<PartEntity> parts = sale.getCar().getParts();
                        double sum = parts.stream().mapToDouble(part -> part.getPrice().doubleValue()).sum();
                        return sum * (1.00 - discount.doubleValue());
                    }).sum()).setScale(2, RoundingMode.HALF_UP));

                    return customerDto;
                })
                .sorted((a, b) -> {
                    int result = Double.compare(b.getSpentMoney().doubleValue(), a.getSpentMoney().doubleValue());
                    if (result == 0) {
                        return Integer.compare(b.getCountCars(), a.getCountCars());
                    }
                    return result;
                })
                .collect(Collectors.toSet()));
        xmlParser.exportXML(customerRoot2Dto, OUT_DIR_XML_FILEPATH + OUT_CUSTOMER_SALES_FILE);
    }
}
