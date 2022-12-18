package com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain;

import org.springframework.data.repository.Repository;

public interface BookRepository extends Repository<Book, Long> {
    Book save(Book book);

    Book getReferenceById(Long bookId);

    void deleteById(Long bookId);
}
