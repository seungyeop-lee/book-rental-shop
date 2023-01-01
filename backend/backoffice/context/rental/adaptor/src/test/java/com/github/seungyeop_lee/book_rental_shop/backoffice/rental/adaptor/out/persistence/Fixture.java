package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import lombok.Getter;

class Fixture {
    @Getter
    private static final RentalId rentalId = new RentalId(1L);
}
