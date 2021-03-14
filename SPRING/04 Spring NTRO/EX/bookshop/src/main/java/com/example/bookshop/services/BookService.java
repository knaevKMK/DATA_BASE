package com.example.bookshop.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface BookService {
    void seedBooks() throws IOException;
}
