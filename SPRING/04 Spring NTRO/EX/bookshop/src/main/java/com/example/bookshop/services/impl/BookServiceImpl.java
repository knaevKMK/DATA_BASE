package com.example.bookshop.services.impl;

import com.example.bookshop.entities.*;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.repositories.BookRepository;
import com.example.bookshop.services.AuthorService;
import com.example.bookshop.services.BookService;
import com.example.bookshop.services.CategoryService;
import com.example.bookshop.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.example.bookshop.constants.GlobalConstants.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorRepository authorRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {

        if (bookRepository.count() != 0) {
            return;
        }

        String[] fileContent = fileUtil.readFileContent(BOOKS_FILE_PATH);
        Arrays.stream(fileContent).forEach(r -> {
            String[] params = r.split("\\s", 6);
            //      Arrays.stream(params).forEach(System.out::println);
            Book book = new Book();
            book.setAuthor(this.getAuthor());
            book.setEditionType(EditionType.values()[Integer.parseInt(params[0])]);
            book.setReleaseDate(LocalDate.parse(params[1], DateTimeFormatter.ofPattern("d/M/yyyy")));
            book.setCopies(Integer.parseInt(params[2]));
            book.setPrice(new BigDecimal(params[3]));
            book.setAgeRestriction(AgeRestriction.values()[Integer.parseInt(params[4])]);
            book.setTitle(params[5]);
            book.setCategories(this.getCategories());
//            System.out.println(book);
              this.bookRepository.saveAndFlush(book);
        });
    }

    private Set<Category> getCategories() {
        Random random = new Random();
        Set<Category> temp = new HashSet<>();
        int randomCount = random.nextInt(4);
      //  System.out.println("Categories: random" + randomCount);
        for (int i = 1; i <= randomCount; i++) {
            long randomId = random.nextInt((int) (this.categoryService.getAllCategoriesCount())) + 1;
     //       System.out.println("randomId" + randomId);
            Category category = this.categoryService.findCategoryById(randomId);
            temp.add(category);
       //     System.out.println(category.toString());
        }
        return temp;

    }


    private Author getAuthor() {
        Random random = new Random();
        long randomId = random.nextInt((int) (this.authorService.getAllAuthorsCount())) + 1;
     //   System.out.println("Author");
     //   System.out.println(randomId);
        Author author = this.authorService.findAuthorById(randomId);
       // System.out.println(author.toString());
        return author;
    }
}
