package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.MemberId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Rental {
    private RentalId id;

    private MemberId memberId;

    private List<RentalBook> rentalBookList;

    public Rental(MemberId memberId) {
        this.memberId = memberId;
        this.rentalBookList = new ArrayList<>();
    }

    public void rentBook(List<BookId> bookIds, OffsetDateTime rentalDateTime) {
        if (bookIds == null || bookIds.isEmpty()) {
            throw new RentalException.InvalidBookIdList("rent book Ids is null");
        }

        for (BookId bookId : bookIds) {
            RentalBook rentalBook = new RentalBook(bookId);
            rentalBook.rentBook(rentalDateTime);
            rentalBookList.add(rentalBook);
        }
    }

    public void returnBook(List<BookId> bookIds, OffsetDateTime rentalReturnDateTime) {
        if (bookIds == null || bookIds.isEmpty()) {
            throw new RentalException.InvalidBookIdList("return book Ids is null");
        }

        for (BookId bookId : bookIds) {
            for (RentalBook rentalBook : rentalBookList) {
                if (rentalBook.getBookId().equals(bookId)) {
                    rentalBook.returnBook(rentalReturnDateTime);
                }
            }
        }
    }
}
