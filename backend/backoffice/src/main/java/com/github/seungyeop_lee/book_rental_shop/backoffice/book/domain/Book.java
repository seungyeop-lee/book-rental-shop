package com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {
    private BookId id;
    private String title;
    private String isbn;

    @Builder
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }
}
