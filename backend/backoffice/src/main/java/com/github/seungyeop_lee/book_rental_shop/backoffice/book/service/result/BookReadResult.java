package com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.result;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import lombok.Data;

@Data
public class BookReadResult {
    private Long id;
    private String title;
    private String isbn;

    public static BookReadResult of(Book book) {
        return new BookReadResult() {{
            setId(book.getId());
            setTitle(book.getTitle());
            setIsbn(book.getIsbn());
        }};
    }
}
