package com.json_xml.parse;

import com.json_xml.parse.services.*;
import com.json_xml.parse.util.IOUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;


import static com.json_xml.parse.constants.Paths.*;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final SaleService saleService;
    private final PartService partService;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CarService carService;
    private final IOUtil ioUtil;

    public CommandLineRunnerImpl(CustomerService customerService,
                                 SupplierService supplierService,
                                 SaleService saleService,
                                 PartService partService,
                                 UserService userService,
                                 ProductService productService,
                                 CategoryService categoryService,
                                 CarService carService,
                                 IOUtil ioUtil) {
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.saleService = saleService;
        this.partService = partService;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.carService = carService;
        this.ioUtil = ioUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        ioUtil.print("\n!!!\nIF YOUR DB(spring_json_xml_parse) IS NOT EMPTY DATA WILL NOT UPLOAD FROM JSON FILES\n!!!\n");
        seedData();

        ioUtil.print(EX_1, EX_2, EX_3, EX_4, EX_5, EX_6, EX_7, EX_8, EX_9, EX_10, END, REPORT_EX_CHOOSE);

        String input = ioUtil.read();

        while (!input.toLowerCase().equals("stop")) {
            switch (input) {
                case "1":
                    ioUtil.print(EX_1);
                    productService.productInRangeOrderByPriceWithoutBuyer(BigDecimal.valueOf(500), BigDecimal.valueOf(100));
                    ioUtil.print("\nFile: " + OUT_PRODUCT_IN_RANGE + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;
                case "2":
                    ioUtil.print(EX_2);
                    userService.findSellersAndSoldProduct();
                    ioUtil.print("\nFile: " + OUT_SELLER_SOLD_PRODUCT + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;
                case "3":
                    ioUtil.print(EX_3);
                    categoryService.categoriesOrderByProductCount();
                    ioUtil.print("\nFile: " + OUT_CATEGORY_PRODUCT_COUNT_TOTAL_PRICE + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;
                case "4":
                    ioUtil.print(EX_4);
                    userService.getStatisticUsersWithTheirSoldProducts();
                    ioUtil.print("\nFile: " + OUT_USER_PRODUCT + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;
                case "5":
                    ioUtil.print(EX_5);
                    customerService.getALLOrderByBirthDateAsc();
                    ioUtil.print("\nFile: " + OUT_ORDERED_CUSTOMER_FILE + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;
                case "6":
                    ioUtil.print(EX_6);
                    carService.getALLByAndOrderByMake("Toyota");
                    ioUtil.print("\nFile: " + OUT_CAR_TOYOTA_FILE + " was created in: " + OUT_DIR_XML_FILEPATH);

                    break;
                case "7":
                    ioUtil.print(EX_7);
                   supplierService.getLocal();
                    ioUtil.print("\nFile: " + OUT_SUPPLIER_LOCAL_FILE + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;
                case "8":
                    ioUtil.print(EX_8);
                    carService.getAllByCar();
                    ioUtil.print("\nFile: " + OUT_CAR_PARTS_FILE + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;
                case "9":
                    ioUtil.print(EX_9);
                    customerService.getCustomers();
                   ioUtil.print("\nFile: " + OUT_CUSTOMER_SALES_FILE + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;
                case "10":
                    ioUtil.print(EX_10);
                    saleService.getWithDiscounts();
                    ioUtil.print("\nFile: " + OUT_SALES_DISCOUNT_FILE + " was created in: " + OUT_DIR_XML_FILEPATH);
                    break;

                default:
                    ioUtil.print("Bad input");
            }
            ioUtil.print(EX_1, EX_2, EX_3, EX_4, EX_5, EX_6, EX_7, EX_8, EX_9, EX_10, END, REPORT_EX_CHOOSE);
            input = ioUtil.read();
            System.out.println(input);
        }
    }


    private void seedData() throws IOException, JAXBException {
//part1
        userService.seedData();
        categoryService.seedData();
        productService.seedData();
        //part2
        supplierService.seedData();
        partService.seedData();
        carService.seedData();
        customerService.seedData();
       saleService.seedData();
    }
}
