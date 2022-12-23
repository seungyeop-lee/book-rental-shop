package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.in.web.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.result.BookReadResult;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookReadResponse {
    private Long id;
    private String title;
    private String isbn;

    public BookReadResponse(BookReadResult input) {
        this.id = input.getId().getId();
        this.title = input.getTitle();
        this.isbn = input.getIsbn();
    }
}
