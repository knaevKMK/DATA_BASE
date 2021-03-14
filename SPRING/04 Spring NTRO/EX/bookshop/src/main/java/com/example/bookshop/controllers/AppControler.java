package com.example.bookshop.controllers;

import com.example.bookshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;


@Controller
public class AppControler implements CommandLineRunner {
    private final CategoryService categoryService;

@Autowired
    public AppControler(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public void run(String... args) throws Exception {
    this.categoryService.seedCategories();
    }
}
