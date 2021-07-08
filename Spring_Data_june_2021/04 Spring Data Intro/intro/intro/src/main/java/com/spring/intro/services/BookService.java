package com.spring.intro.services;

import com.spring.intro.models.entities.BookEntity;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedData() throws IOException;

    List<String> getAllBooksAfterYear(int i);

    List<String> getAllAuthorsWithBookReleaseBeforeYear(int i);

    List<String> getBooksByAuthorNamesOrderByReleaseDateDescThenByTitleAsc(String fullName);
}
