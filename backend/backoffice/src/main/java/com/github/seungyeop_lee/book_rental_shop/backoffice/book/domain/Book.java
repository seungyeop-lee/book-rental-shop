package com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    protected Long id;

    protected String title;

    @Column(unique = true)
    protected String isbn;

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public void update(Book book) {
        this.title = book.title;
        this.isbn = book.isbn;
    }
}
