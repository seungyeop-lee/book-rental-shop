package com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Book {
    private final BookId id;
    private final String title;
    private final String isbn;

    @Builder
    public Book(BookId id, String title, String isbn) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
    }
}
