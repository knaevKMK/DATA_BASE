package com.spring.intro.services.impl;

import com.spring.intro.constants.GlobalConstants;
import com.spring.intro.models.entities.AuthorEntity;
import com.spring.intro.repositories.AuthorRepository;
import com.spring.intro.services.AuthorService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedData() throws IOException {
        if (authorRepository.count() != 0) {
            return;
        }
        Files.readAllLines(Path.of(GlobalConstants.AUTHORS_FILE_PATH))
                .stream()
                .filter(r -> !r.isEmpty())
                .forEach(r -> {
                    String[] fullName = r.split("\\s+");
                    AuthorEntity author = new AuthorEntity();
                    if (fullName.length == 1) {
                        author.setLastName(fullName[0]);
                    } else {
                        author
                                .setFirstName(fullName[0])
                                .setLastName(fullName[1]);
                    }
                    authorRepository.save(author);
                });
    }

    @Override
    public AuthorEntity getRandomAuthor() {
        Long id = ThreadLocalRandom.current().nextLong(1L, authorRepository.count() + 1);
        return this.authorRepository.findById(id).orElse(null);

    }

    @Override
    public List<String> getAllAuthorsOrderByCountOfBooksDesc() {
        return authorRepository.findAllByBooksSize()
                .stream().map(a -> String.format("%s %s %d",
                        a.getFirstName(), a.getLastName(), a.getBooks().size()))
                .collect(Collectors.toList());

    }
}
