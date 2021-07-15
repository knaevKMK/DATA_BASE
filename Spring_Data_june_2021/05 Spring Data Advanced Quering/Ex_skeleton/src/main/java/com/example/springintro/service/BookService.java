package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);



    List<String> getBooksTitlesByAgeRestriction(AgeRestriction ageRestriction);

    List <String> getBooksByEditionTypeAndCopiesLowerThan(EditionType type, int i);

    List<String> getBooksNotInPriceRange(BigDecimal lowerValue, BigDecimal higherValue);

    List<String> findAllBooksNotReleasedInYear(int year);

    List<String> findAllBooksReleasedBefore(String release);

    List<String> findAllBooksByTitleContain(String regex);

    List<String> findAllBooksByAuthorLastNameContain(String regex);

    List<String> countBooksWithTitleLenghtOver(int lenght);

    List<String> countBooksByCopiesGropuByAuthor() throws SQLException;

    List<String> findBookTitleAndEditionTypeAndAgerestricitonAndPriceByTitle(String title);

    List<String> increaseBookCopiesReleasedAfter(String date, int copies);

    List<String> deleteBooksByCopiesUnder(int copiesTarget);

    List<String> getBooksCountByAuthorName(String authorFullName);
}
