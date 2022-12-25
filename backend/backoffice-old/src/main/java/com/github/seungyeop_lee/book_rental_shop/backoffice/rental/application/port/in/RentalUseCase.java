package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto.RentalCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto.ReturnCommand;

public interface RentalUseCase {
    void rentalBook(RentalCommand rentalCommand);

    void returnBook(ReturnCommand returnCommand);
}
