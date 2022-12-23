package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.BookId;

public interface BookDeleter {
    void deleteById(BookId bookId);
}
