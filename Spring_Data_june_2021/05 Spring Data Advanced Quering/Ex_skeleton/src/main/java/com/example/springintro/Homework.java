package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Homework {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void Run(CategoryService categoryService, AuthorService authorService, BookService bookService) throws IOException {
        System.out.println("Welcome to 'Exercises: Spring Data Advanced Quering' ");
        System.out.println("Enter 'END' to terminate application or insert EX number (between 1 and 14): ");
        String input = reader.readLine();


        while (!input.equalsIgnoreCase("END")) {
            List<String> result = new ArrayList<>();
            switch (input) {
                case "1":
                    printExName(1);
                    printSOUT("Please enter restriction: \n for Example: miNor , teEn, AdulT ");
                    String param = reader.readLine();
                    result = bookService.getBooksTitlesByAgeRestriction(AgeRestriction.valueOf(param.toUpperCase()));
                    break;

                case "2":
                    printExName(2);
                    result = bookService.getBooksByEditionTypeAndCopiesLowerThan(EditionType.GOLD, 5000);
                    break;

                case "3":
                    printExName(3);
                    result = bookService.getBooksNotInPriceRange(new BigDecimal("5.00"), new BigDecimal("40.00"));
                    break;
                case "4":
                    printExName(4);
                    printSOUT("Enter released year:");
                    int year = Integer.parseInt(reader.readLine());
                    result = bookService.findAllBooksNotReleasedInYear(year);
                    break;
                case "5":
                    printExName(5);
                    printSOUT("Enter date in format dd-MM-yyyy");

                    result = bookService.findAllBooksReleasedBefore(reader.readLine());
                    break;
                case "6":
                    printExName(6);
                    printSOUT("Enter char sequence:");
                    result = authorService.findAllAuthorsByFirstnameEndWith(reader.readLine());
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "12":
                    break;
                case "13":
                    break;
                case "14":
                    break;
            }
            if (result.size() == 1) {
                printSOUT("No matches");
            } else {
                result.forEach(System.out::println);
            }

            printSOUT("Enter 'END' to terminate application or insert EX number (between 1 and 14): ");
            input = reader.readLine();

        }
    }


    private static void printSOUT(String s) {
        System.out.println(s);
    }

    private static void printExName(int i) {
        String exercise = "wrong choise";
        switch (i) {
            case 1:
                exercise = " Ex.1 Books Titles by Age Restriction ";
                break;
            case 2:
                exercise = " Ex.2.\tGolden Books ";
                break;
            case 3:
                exercise = " Ex.3.\tBooks by Price";
                break;
            case 4:
                exercise = " Ex.4.\tNot Released Books";
                break;
            case 5:
                exercise = " Ex.5.\tBooks Released Before Date";
                break;
            case 6:
                exercise = " Ex.6.\tAuthors Search";
                break;
            case 7:
                exercise = " Ex.7.\tBooks Search";
                break;
            case 8:
                exercise = " Ex.8.\tBook Titles Search";
                break;
            case 9:
                exercise = " Ex.9.\tCount Books";
                break;
            case 10:
                exercise = " Ex.10.\tTotal Book Copies ";
                break;
            case 11:
                exercise = " Ex.11.\tReduced Book";
                break;
            case 12:
                exercise = " Ex.12.\t* Increase Book Copies";
                break;
            case 13:
                exercise = " Ex.13.\t* Remove Books";
                break;
            case 14:
                exercise = " Ex.14.\t* Stored Procedure";
                break;
        }
        printSOUT("\n ++++++++++++++++++++++++++++++++++++++++++ \n" +
                exercise +
                "\n +++++++++++++++++++++++++++++++++++++++++\\n\");\n");
    }
}