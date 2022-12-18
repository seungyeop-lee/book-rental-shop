package com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
