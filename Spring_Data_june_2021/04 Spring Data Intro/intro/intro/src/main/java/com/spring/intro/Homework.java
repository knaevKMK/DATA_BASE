package com.spring.intro;

import com.spring.intro.constants.GlobalConstants;
import com.spring.intro.services.AuthorService;
import com.spring.intro.services.BookService;
import com.spring.intro.services.CategoryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.spring.intro.constants.GlobalConstants.*;

public class Homework {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void seedData(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        try {
            authorService.seedData();
            categoryService.seedData();
            bookService.seedData();
        } catch (Exception e) {
            System.out.println("impossible load data to DB");
            System.out.println(e.getMessage());
        }
    }


    public static void start(CategoryService categoryService, AuthorService authorService, BookService bookService) throws IOException {


        System.out.println(MSG_CHOOSE + System.lineSeparator() + MSG_EX);
        String read = reader.readLine();
        while (!read.equalsIgnoreCase("end")) {

            switch (read) {
                case "1":
                    bookService.getAllBooksAfterYear(2000)
                            .forEach(System.out::println);
                    break;
                case "2":
                    bookService.getAllAuthorsWithBookReleaseBeforeYear(1990)
                            .forEach(System.out::println);
                    break;
                case "3":
                    authorService.getAllAuthorsOrderByCountOfBooksDesc()
                            .forEach(System.out::println);
                    break;
                case "4":
                    bookService.getBooksByAuthorNamesOrderByReleaseDateDescThenByTitleAsc("George Powell")
                            .forEach(System.out::println);
                    break;
                default:
                    System.out.println("Bad input");
                    break;
            }
            System.out.println(MSG_CHOOSE + System.lineSeparator() + MSG_EX);

            read = reader.readLine();
        }
    }
}
