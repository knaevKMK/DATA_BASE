package com.json.json;

import com.json.json.services.CategoryService;
import com.json.json.services.ProductService;
import com.json.json.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    public CommandLineRunnerImpl(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedData();

    }

    private void seedData() throws IOException {
//        categoryService.seedDataFromJson();
//        userService.seedDataFromJson();
       productService.seedDataFromJson();
    }
}
