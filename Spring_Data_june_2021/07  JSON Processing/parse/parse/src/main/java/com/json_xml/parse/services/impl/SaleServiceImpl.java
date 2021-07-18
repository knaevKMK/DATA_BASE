package com.json_xml.parse.services.impl;

import com.google.gson.Gson;
import com.json_xml.parse.models.entities.partCarSale.SaleEntity;
import com.json_xml.parse.repositories.SaleRepository;
import com.json_xml.parse.services.CarService;
import com.json_xml.parse.services.CustomerService;
import com.json_xml.parse.services.SaleService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final IOUtil ioUtil;
    private final CarService carService;
    private final CustomerService customerService;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper, Gson gson,
                           ValidationUtil validationUtil, IOUtil ioUtil, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void seedData() throws IOException {

        ioUtil.print("sale_table data has " + saleRepository.count() + " records\n" +
                "Enter number of sales, wich you want to create?");

        int count = Integer.parseInt(ioUtil.read());


//. Finally, import the sales records by
// -randomly selecting:
//              a car,
//              customer and the
//              amount of discount to be applied (discounts can be 0%, 5%, 10%, 15%, 20%, 30%, 40% or 50%).
        ioUtil.print("Data will seed" + "\nPLEASE WAIT...");
        List<String> report = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            SaleEntity sale = new SaleEntity();

            try {
                sale.setCar(carService.getRandomById());
                sale.setCustomer(customerService.getRandomById());
                sale.setDiscount(getRandomDiscount());
                saleRepository.save(sale);
                report.add("Success add sale: \n\tSold car: " + sale.getCar().getMake() + " " + sale.getCar().getModel()
                        + "\n\tto Customer: " + sale.getCustomer().getName());

            } catch (Exception e) {
                report.add(e.getMessage());
                i--;
            }
        }
        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");

    }



    private BigDecimal getRandomDiscount() {
        int[] discountArr = {0, 5, 10, 15, 20, 20, 40, 50};
        int i = ThreadLocalRandom.current().nextInt(8);
        return BigDecimal.valueOf(discountArr[i] * 1.0 / 100);
    }
}
