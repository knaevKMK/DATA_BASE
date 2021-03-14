package com.example.bookshop.services;

import com.example.bookshop.entities.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthors() throws IOException;

    long getAllAuthorsCount();
    Author findAuthorById(Long id);
}
