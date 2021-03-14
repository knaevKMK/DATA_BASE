package com.example.bookshop.services.impl;

import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.services.AuhtorService;
import com.example.bookshop.utils.FileUtil;

public class AuthorServiceImpl implements AuhtorService {
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() {

    }
}
