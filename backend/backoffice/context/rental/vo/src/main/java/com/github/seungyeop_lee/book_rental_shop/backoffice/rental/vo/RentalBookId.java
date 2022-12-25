package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RentalBookId {
    private final Long id;

    public RentalBookId(Long id) {
        this.id = id;
    }
}
