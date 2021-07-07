package com.example.bookshop.controllers;

import com.example.bookshop.services.AuthorService;
import com.example.bookshop.services.BookService;
import com.example.bookshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;


@Controller
public class AppControler implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookSrveice;

    @Autowired
    public AppControler(CategoryService categoryService, AuthorService authorService, BookService bookSrveice) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookSrveice = bookSrveice;
    }


    @Override
    public void run(String... args) throws Exception {
//        this.categoryService.seedCategories();
//        this.authorService.seedAuthors();
//        this.bookSrveice.seedBooks();
    }
}
