package com.spring.intro.repositories;

import com.spring.intro.models.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Set<BookEntity> findAllByReleaseDateAfter(LocalDate releaseDate);

    List<BookEntity> findAllByReleaseDateBefore(LocalDate date);

    @Query ("SELECT b FROM BookEntity AS b " +
            " WHERE CONCAT(b.author.firstName, ' ', b.author.lastName) = 'George Powell' " +
            "ORDER BY b.releaseDate DESC, b.title ASC")
    List<BookEntity> getBooksByAuthorNamesOrderByReleaseDateDescThenByTitleAsc(String fullName);
}
