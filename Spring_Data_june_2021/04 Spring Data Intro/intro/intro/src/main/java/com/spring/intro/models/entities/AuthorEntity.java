package com.spring.intro.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private Set<BookEntity> books;

    public AuthorEntity() {
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author", targetEntity = BookEntity.class)
    public Set<BookEntity> getBooks() {
        return books;
    }

    public AuthorEntity setBooks(Set<BookEntity> books) {
        this.books = books;
        return this;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public AuthorEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AuthorEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
