package com.spring.intro.repositories;

import com.spring.intro.models.entities.AuthorEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("SELECT a FROM AuthorEntity AS a ORDER BY a.books.size DESC")
    List<AuthorEntity> findAllByBooksSize();
}
