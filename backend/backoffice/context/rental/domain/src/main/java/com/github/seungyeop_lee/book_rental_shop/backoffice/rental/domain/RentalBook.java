package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalBookId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalBook {
    private RentalBookId id;

    private final BookId bookId;

    private OffsetDateTime rentalDateTime;

    private OffsetDateTime rentalReturnDateTime;

    public RentalBook(BookId bookId) {
        this.bookId = bookId;
    }

    public void rentBook(OffsetDateTime rentalDateTime) {
        this.rentalDateTime = rentalDateTime;
    }

    public void returnBook(OffsetDateTime returnDateTime) {
        this.rentalReturnDateTime = returnDateTime;
    }
}
