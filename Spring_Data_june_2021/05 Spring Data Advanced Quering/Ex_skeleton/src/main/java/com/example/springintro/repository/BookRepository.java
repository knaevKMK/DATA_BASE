package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    @Query("SELECT b FROM Book AS b WHERE b.ageRestriction = :ageRestriction")
    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    @Query("SELECT b FROM Book  AS b WHERE b.editionType= :type AND b.copies < :copies")
    List<Book> findBooksByEditionTypeAndCopiesLowerThan(EditionType type, int copies);

    @Query("SELECT b FROM Book AS b WHERE b.price NOT BETWEEN :lowerValue AND :higherValue")
    List<Book> findAllBooksByPriceNotInRange(BigDecimal lowerValue, BigDecimal higherValue);

    @Query(value = "SELECT * FROM books AS b WHERE year(b.release_date) <> :year", nativeQuery = true)
    List<Book> findAllBooksNotReleasedInYear(int year);

    List<Book> findAllByTitleContains(String regex);

    @Query("SELECT b FROM Book AS b WHERE b.author.lastName LIKE CONCAT(:regex,'%')")
    List<Book> findBooksByAuthorLastNameStartsWith(String regex);

    @Query(value = "SELECT COUNT(*) FROM books AS b WHERE LENGth(b.title) > :longer "
            , nativeQuery = true)
    int countBooksByTitleLengthLongerThan(int longer);

    @Query(value = "SELECT SUM(b.copies) as countCopies,concat(b.author.firstName,' '   , b.author.lastName) " +
            " FROM Book  AS b GROUP BY  b.author ORDER BY countCopies DESC")
    List<Object[]> countBookCopiesByAuthor();

    @Query("SELECT b.title, b.editionType,b.ageRestriction,b.price " +
            " FROM Book AS b " +
            " WHERE b.title=:title")
    List<Object[]> findBookTitleAndEditionTypeAndAgeRestrictionAndPriceByTitle(String title);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies= b.copies + :copies WHERE b.releaseDate > :date")
    int increaseBooKCopiesByReleaseDate(LocalDate date, int copies);

    @Modifying
    @Query("DELETE  FROM Book b WHERE b.copies< :copy")
    int deleteBooksByCopiesUnder(int copy);

    @Procedure("COUNT_BOOKS_BY_AUTHOR_FULL_NAME")
    List<Object[]> getCountBooksByAuthorFirstNameAndAuthorLastName(@Param("first_name") String first_name,
                                                                   @Param("last_name") String last_name);
}
