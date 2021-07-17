package com.json_xml.parse;

import com.json_xml.parse.constants.Paths;
import com.json_xml.parse.services.CategoryService;
import com.json_xml.parse.services.ProductService;
import com.json_xml.parse.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public CommandLineRunnerImpl(UserService userService,
                                 ProductService productService,
                                 CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        System.out.println("\nEnter 'END' to terminate program or EX number: \n");
        String read = reader.readLine();

        while (!read.equalsIgnoreCase("end")) {
            switch (read) {
                case "1":
                    write(productService.
                                    productInRangeOrderByPriceWithoutBuyer(BigDecimal.valueOf(500), BigDecimal.valueOf(100)),
                            Paths.OUT_DIR_JSON_FILEPATH + "product-in-range.json");
                    break;
                case "2":
                    write(
                            userService.findSellersAndSoldProduct()
                            , Paths.OUT_DIR_JSON_FILEPATH + "seller-success-sold-product.json"
                    );
                    break;
                case "3":
                    write( categoryService.categoriesOrderByProductCount()
                            , Paths.OUT_DIR_JSON_FILEPATH + "category-product-count-total-price.json"
                    );
                    break;
                case "4":write(userService.getStatisticUsersWithTheirSoldProducts()
                        ,Paths.OUT_DIR_JSON_FILEPATH+"users-and_products.json"
                );
                    break;
                case "5":
                    break;
            }
            System.out.println("\nEnter 'END' to terminate program or EX number: \n");

            read = reader.readLine();
        }
    }


    private void write(String content, String path) throws IOException {
//        System.out.println(content);
//        System.out.println(path);
        Files.write(Path.of(path), Collections.singleton(content));
    }

    private void seedData() throws IOException {

        userService.seedData();
        categoryService.seedData();
        productService.seedData();


    }
}
