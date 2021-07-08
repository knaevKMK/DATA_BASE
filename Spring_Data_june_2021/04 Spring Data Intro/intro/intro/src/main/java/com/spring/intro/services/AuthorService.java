package com.spring.intro.services;

import com.spring.intro.models.entities.AuthorEntity;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AuthorService {
    void seedData() throws IOException;

    AuthorEntity getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfBooksDesc();
}
