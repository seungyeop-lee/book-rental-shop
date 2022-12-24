package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import lombok.Data;

@Data
public class BookUpdateCommand {
    private String title;
    private String isbn;

    public Book mapToBook() {
        return Book.builder().title(title).isbn(isbn).build();
    }
}
