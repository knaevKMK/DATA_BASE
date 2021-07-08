package com.spring.intro.services.impl;

import com.spring.intro.constants.GlobalConstants;
import com.spring.intro.models.entities.AuthorEntity;
import com.spring.intro.models.entities.BookEntity;
import com.spring.intro.models.entities.CategoryEntity;
import com.spring.intro.models.entities.enums.EditionType;
import com.spring.intro.models.entities.enums.Restriction;
import com.spring.intro.repositories.BookRepository;
import com.spring.intro.services.AuthorService;
import com.spring.intro.services.BookService;
import com.spring.intro.services.CategoryService;
import org.hibernate.type.LocalDateType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    //do not work if duplicate categories per book
    @Override
    public void seedData() throws IOException {
        if (bookRepository.count() != 0) {
            return;
        }
        Files.readAllLines(Path.of(GlobalConstants.BOOKS_FILE_PATH))
                .stream()
                .filter(r -> !r.isEmpty())
                .forEach(r -> {
                    String[] params = r.split("\\s+", 6);
                    BookEntity book = new BookEntity();
                    AuthorEntity author = this.authorService.getRandomAuthor();
                    Set<CategoryEntity> categories = this.categoryService.getRandomCategories();
                    System.out.println(categories);
                    book
                            .setAuthor(author)
                            .setEditionType(EditionType.values()[Integer.parseInt(params[0])])
                            .setReleaseDate(LocalDate.parse(params[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                            .setCopies(Integer.parseInt(params[2]))
                            .setPrice(new BigDecimal(params[3]))
                            .setAgeRestriction(Restriction.values()[Integer.parseInt(params[4])])
                            .setTitle(params[5])
                            .setCategories(categories)
                    ;

                    this.bookRepository.save(book);
                });
    }

    @Override
    public List<String> getAllBooksAfterYear(int i) {
        LocalDate releaseDate = LocalDate.of(i, 1, 1);
        return bookRepository.findAllByReleaseDateAfter(releaseDate).stream().map(BookEntity::getTitle).toList();
    }

    @Override
    public List<String> getAllAuthorsWithBookReleaseBeforeYear(int i) {
        return bookRepository.findAllByReleaseDateBefore(LocalDate.of(i, 1, 1))
                .stream().map(b -> String.format("%s %s"
                        , b.getAuthor().getFirstName()
                        , b.getAuthor().getLastName())).collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByAuthorNamesOrderByReleaseDateDescThenByTitleAsc(String fullName) {
        return bookRepository.getBooksByAuthorNamesOrderByReleaseDateDescThenByTitleAsc(fullName)
                .stream().map(b -> String.format("%s %s %d",
                        b.getTitle(), b.getReleaseDate(), b.getCopies()))
                .collect(Collectors.toList());
    }


}
