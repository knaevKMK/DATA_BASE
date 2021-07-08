package com.spring.intro.models.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {

    private String name;
    private Set<BookEntity> books;

    public CategoryEntity() {
    }

    @ManyToMany(mappedBy = "categories", targetEntity = BookEntity.class)
    public Set<BookEntity> getBooks() {
        return books;
    }

    public CategoryEntity setBooks(Set<BookEntity> books) {
        this.books = books;
        return this;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }
}
