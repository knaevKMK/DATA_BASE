package com.spring.intro.models.entities;

import com.spring.intro.models.entities.enums.EditionType;
import com.spring.intro.models.entities.enums.Restriction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {

    private String title;
    private String description;
    private EditionType editionType;
    private int copies;
    private BigDecimal price;
    private LocalDate releaseDate;
    private Restriction ageRestriction;
    private Set<CategoryEntity> categories;
    private AuthorEntity author;


    public BookEntity() {
    }

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}
            )
    public AuthorEntity getAuthor() {
        return this.author;
    }


    @ManyToMany
    public Set<CategoryEntity> getCategories() {
        return this.categories;
    }


    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }


    @Column(name = "title", length = 50, nullable = false)
    public String getTitle() {
        return title;
    }


    @Column(name = "decription", length = 1000)
    public String getDescription() {
        return description;
    }


    @Enumerated(EnumType.ORDINAL)
    public EditionType getEditionType() {
        return editionType;
    }


    @Column(name = "copies")
    public int getCopies() {
        return copies;
    }


    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }


    @Enumerated(EnumType.ORDINAL)
    public Restriction getAgeRestriction() {
        return ageRestriction;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BookEntity setEditionType(EditionType editionType) {
        this.editionType = editionType;
        return this;
    }

    public BookEntity setCopies(int copies) {
        this.copies = copies;
        return this;
    }

    public BookEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BookEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public BookEntity setAgeRestriction(Restriction ageRestriction) {
        this.ageRestriction = ageRestriction;
        return this;
    }

    public BookEntity setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public BookEntity setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }
}
