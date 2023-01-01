package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.Rental;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;

public interface RentalSaver {
    RentalId save(Rental rental);
}
