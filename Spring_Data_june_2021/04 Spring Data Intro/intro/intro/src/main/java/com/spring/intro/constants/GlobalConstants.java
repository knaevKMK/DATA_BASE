package com.spring.intro.constants;

public class GlobalConstants {
    public static final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";
    public static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";
    public static final String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.txt";
    public static final String MSG_CHOOSE = "Please enter 'END' to terminate the app \n or  number of EX (between 1 and 4) :";
    public static final String MSG_EX =
            "                1.Get all books after the year 2000. Print only their titles.\n" +
            "                2.Get all authors with at least one book with release date before 1990. Print their first name and last name.\n" +
            "                3.Get all authors, ordered by the number of their books (descending). Print their first name, last name and book count.\n" +
            "                4.Get all books from author George Powell, ordered by their release date (descending), then by book title (ascending). Print the book's title, release date and copies.\\n";

}
