package com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import lombok.Data;

@Data
public class BookCreateParameter {
    private String title;
    private String isbn;

    public Book buildBook() {
        return new Book(title, isbn);
    }
}
