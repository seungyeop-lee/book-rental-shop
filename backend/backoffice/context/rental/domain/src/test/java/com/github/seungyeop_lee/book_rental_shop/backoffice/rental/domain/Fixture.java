package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.MemberId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

class Fixture {
    static class RentalFixture {
        @Getter
        private static final RentalId rentalId = new RentalId(1L);
        @Getter
        private static final MemberId memberId = new MemberId(1L);

        public static Rental emptyRental() {
            return new Rental(memberId);
        }

        public static Rental rentedRental() {
            return Rental.builder()
                    .id(rentalId)
                    .memberId(memberId)
                    .rentalBookList(RentalBookFixture.rentedRentalBookList())
                    .build();
        }
    }

    static class RentalBookFixture {
        @Getter
        private static final List<BookId> rentedBookIds = List.of(
                new BookId(1L),
                new BookId(2L),
                new BookId(3L),
                new BookId(4L),
                new BookId(5L),
                new BookId(6L)
        );
        private static final OffsetDateTime rentalDateTime = OffsetDateTime.of(
                2021,
                1,
                1,
                0,
                0,
                0,
                0,
                OffsetDateTime.now().getOffset()
        );

        public static List<RentalBook> rentedRentalBookList() {
            return rentedBookIds.stream()
                    .map(bookId -> rentedRentalBook(bookId, rentalDateTime))
                    .toList();
        }

        public static RentalBook rentedRentalBook(BookId bookId, OffsetDateTime rentalDateTime) {
            return RentalBook.builder()
                    .bookId(bookId)
                    .rentalDateTime(rentalDateTime)
                    .build();
        }
    }
}
