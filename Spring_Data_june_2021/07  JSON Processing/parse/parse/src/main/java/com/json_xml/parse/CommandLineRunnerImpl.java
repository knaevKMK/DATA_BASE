package com.json_xml.parse;

import com.json_xml.parse.services.CategoryService;
import com.json_xml.parse.services.ProductService;
import com.json_xml.parse.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

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
    }

    private void seedData() throws IOException {
        userService.seedData();
//        categoryService.seedData();
//        productService.seedData();

    }
}
