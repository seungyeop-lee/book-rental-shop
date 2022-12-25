package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.Rental;

public interface RentalSaver {
    void save(Rental rental);
}
