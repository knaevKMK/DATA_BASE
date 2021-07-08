package com.spring.intro;

import com.spring.intro.services.AuthorService;
import com.spring.intro.services.BookService;
import com.spring.intro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
public class ApplicationRunner implements CommandLineRunner {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public ApplicationRunner(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    public void run(String... args) throws IOException {
        Homework.seedData( categoryService,authorService,bookService);
        Homework.start(categoryService,authorService,bookService);

    }


}
