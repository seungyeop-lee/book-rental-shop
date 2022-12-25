package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookCreateCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookReadResult;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookUpdateCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;

public interface BookUseCase {
    BookId registerBook(BookCreateCommand param);

    BookReadResult readBook(BookId bookId);

    void updateBook(BookId bookId, BookUpdateCommand param);

    void deleteBook(BookId bookId);
}
