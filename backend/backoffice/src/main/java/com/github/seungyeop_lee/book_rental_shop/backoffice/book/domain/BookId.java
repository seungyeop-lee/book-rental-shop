package com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class BookId {
    Long id;

    public BookId(Long id) {
        this.id = id;
    }
}
