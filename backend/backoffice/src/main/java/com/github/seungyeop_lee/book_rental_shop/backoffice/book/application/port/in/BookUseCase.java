package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.param.BookCreateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.param.BookUpdateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.result.BookReadResult;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.BookId;

public interface BookUseCase {
    BookId registerBook(BookCreateParameter param);

    BookReadResult readBook(BookId bookId);

    void updateBook(BookId bookId, BookUpdateParameter param);

    void deleteBook(BookId bookId);
}
