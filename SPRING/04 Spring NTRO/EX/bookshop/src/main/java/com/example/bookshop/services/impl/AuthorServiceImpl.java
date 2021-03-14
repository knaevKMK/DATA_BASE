package com.example.bookshop.services.impl;

import com.example.bookshop.entities.Author;
import com.example.bookshop.repositories.AuthorRepository;
import com.example.bookshop.services.AuthorService;
import com.example.bookshop.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;

import static com.example.bookshop.constants.GlobalConstants.*;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }
        String[] fileContent = fileUtil.readFileContent(AUTHORS_FILE_PATH);
        Arrays.stream(fileContent).forEach(r -> {
            String[] names = r.split("\\s+");
            Author author;
            if (names.length == 1) {
               author  = new Author(names[0]);
            } else {
                author = new Author(names[0], names[1]);
            }
            this.authorRepository.saveAndFlush(author);
        });
    }



    @Override
    public long getAllAuthorsCount() {
        return this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }
}
