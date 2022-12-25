package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import lombok.Data;

import java.util.List;

@Data
public class ReturnCommand {
    private RentalId rentalId;
    private List<BookId> bookIds;
}
