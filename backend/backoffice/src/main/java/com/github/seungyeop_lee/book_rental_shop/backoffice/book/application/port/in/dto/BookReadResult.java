package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
import lombok.Data;

@Data
public class BookReadResult {
    private BookId id;
    private String title;
    private String isbn;

    public static BookReadResult of(Book book) {
        BookReadResult result = new BookReadResult();
        result.setId(book.getId());
        result.setTitle(book.getTitle());
        result.setIsbn(book.getIsbn());
        return result;
    }
}
