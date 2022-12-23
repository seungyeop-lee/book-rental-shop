package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.out.persistence.entity;

import org.springframework.data.repository.Repository;

public interface BookJpaRepository extends Repository<BookJpaEntity, Long> {
    BookJpaEntity save(BookJpaEntity book);

    BookJpaEntity getReferenceById(Long bookId);

    void deleteById(Long bookId);
}
