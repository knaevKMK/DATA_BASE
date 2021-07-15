package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
        return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksTitlesByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findBooksByAgeRestriction(ageRestriction)
                .stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByEditionTypeAndCopiesLowerThan(EditionType type, int i) {
        return bookRepository.findBooksByEditionTypeAndCopiesLowerThan(type, i)
                .stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksNotInPriceRange(BigDecimal lowerValue, BigDecimal higherValue) {
        return bookRepository.findAllBooksByPriceNotInRange(lowerValue, higherValue).stream()
                .map(b -> String.format("%s - $%.2f", b.getTitle(), b.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksNotReleasedInYear(int year) {
        return this.bookRepository.findAllBooksNotReleasedInYear(year).
                stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksReleasedBefore(String releaseStr) {
        String[] date = releaseStr.split("-");
        LocalDate release = LocalDate.parse(date[2] + "-" + date[1] + "-" + date[0]);
        return this.bookRepository.findAllByReleaseDateBefore(release).stream()
                .map(b -> String.format("%s %s %.2f"
                        , b.getTitle()
                        , b.getEditionType().name()
                        , b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByTitleContain(String regex) {
        return this.bookRepository.findAllByTitleContains(regex)
                .stream().map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorLastNameContain(String regex) {
        return bookRepository.findBooksByAuthorLastNameStartsWith(regex)
                .stream().map(b -> String.format("%s (%s %s)"
                        , b.getTitle()
                        , b.getAuthor().getFirstName()
                        , b.getAuthor().getLastName())).collect(Collectors.toList());
    }

    @Override
    public List<String> countBooksWithTitleLenghtOver(int lenght) {
        return List.of("\nThere are " + this.bookRepository.countBooksByTitleLengthLongerThan(lenght)
                + " books with longer title than " + lenght + " symbols\n");
    }

    @Override
    public List<String> countBooksByCopiesGropuByAuthor() throws SQLException {
        return bookRepository.countBookCopiesByAuthor().stream()
                .map(o -> String.format("%s - %s", o[1], o[0]))
                .collect(Collectors.toList());

    }

    @Override
    public List<String> findBookTitleAndEditionTypeAndAgerestricitonAndPriceByTitle(String title) {
        return bookRepository.findBookTitleAndEditionTypeAndAgeRestrictionAndPriceByTitle(title)
                .stream()
                .map(o -> String.format("%s %s %s %s"
                        , o[0], o[1], o[2], o[3]))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> increaseBookCopiesReleasedAfter(String date, int copies) {
        int resultBooks = bookRepository
                .increaseBooKCopiesByReleaseDate(
                        LocalDate.parse(date.trim(),
                                DateTimeFormatter.ofPattern("dd MMM yyyy")), copies);
        return List.of(String.format("%d books are released after %s, so total of %d book copies were added"
                , resultBooks
                , date
                , resultBooks * copies));
    }

    @Transactional
    @Override
    public List<String> deleteBooksByCopiesUnder(int copiesTarget) {
        int result = bookRepository.deleteBooksByCopiesUnder(copiesTarget);
        return List.of(result == 0
                ? "No books were deleted"
                : String.format("%d book%s were deleted"
                , result
                , result == 1 ? "" : "s"));
    }

    @Override
    public List<String> getBooksCountByAuthorName(String authorFullName) {
        String [] names= authorFullName.split("\\s+");

        List<Object[]> result = bookRepository.getCountBooksByAuthorFirstNameAndAuthorLastName(names[0], names[1]);
        return List.of(String.format("%s has %swritten %sbooks",
                authorFullName
                , result.get(0)[0].equals("0") ? "no " : ""
                , result.get(0)[0].equals("0")? "" : String.valueOf(result)));
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
