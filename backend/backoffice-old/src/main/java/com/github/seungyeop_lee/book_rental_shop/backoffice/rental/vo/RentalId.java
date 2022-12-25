package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RentalId {
    private final Long id;

    public RentalId(Long id) {
        this.id = id;
    }
}
