package com.json.part2;

import com.json.part2.services.CarService;
import com.json.part2.services.SupplierService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CarService carService;
    private final SupplierService supplierService;
    private final Part


    public CommandLineRunnerImpl(CarService carService, SupplierService supplierService) {
        this.carService = carService;
        this.supplierService = supplierService;
    }


    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() {
        String report = null;
        try {
            report = supplierService.seedData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            report+=
        //    report = carService.seedData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(report);
        }
    }
}
