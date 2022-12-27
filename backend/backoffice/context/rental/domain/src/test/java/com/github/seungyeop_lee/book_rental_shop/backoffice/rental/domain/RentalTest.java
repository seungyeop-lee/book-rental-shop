package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RentalTest {

    @DisplayName("책 대여 - 정상")
    @Test
    void rentBook_normal() {
        //given
        Rental rental = Fixture.RentalFixture.emptyRental();

        OffsetDateTime now = OffsetDateTime.now();
        List<BookId> bookIds = List.of(new BookId(1L), new BookId(2L));

        //when
        rental.rentBook(bookIds, now);

        //then
        rental.getRentalBookList().forEach(rentalBook -> {
            assertThat(rentalBook.getBookId()).isIn(bookIds);
            assertThat(rentalBook.getRentalDateTime()).isEqualTo(now);
        });
    }

    @DisplayName("책 대여 - 예외(null 인 반납 책 리스트)")
    @Test
    void rentBook_exception1() {
        //given
        Rental rental = Fixture.RentalFixture.emptyRental();

        OffsetDateTime now = OffsetDateTime.now();

        //when
        Executable when = () -> rental.rentBook(null, now);

        //then
        assertThrows(RentalException.InvalidBookIdList.class, when);
    }

    @DisplayName("책 대여 - 예외(비어있는 반납 책 리스트)")
    @Test
    void rentBook_exception2() {
        //given
        Rental rental = Fixture.RentalFixture.emptyRental();

        OffsetDateTime now = OffsetDateTime.now();
        List<BookId> bookIds = List.of();

        //when
        Executable when = () -> rental.rentBook(bookIds, now);

        //then
        assertThrows(RentalException.InvalidBookIdList.class, when);
    }

    @DisplayName("책 대여 - 예외(대여 시간이 null)")
    @Test
    void rentBook_exception3() {
        //given
        Rental rental = Fixture.RentalFixture.emptyRental();

        List<BookId> bookIds = List.of(new BookId(1L), new BookId(2L));

        //when
        Executable when = () -> rental.rentBook(bookIds, null);

        //then
        assertThrows(RentalException.InvalidRentalDateTime.class, when);
    }

    @DisplayName("책 반납 - 정상")
    @Test
    void returnBook_normal() {
        //given
        Rental rental = Fixture.RentalFixture.rentedRental();

        List<BookId> returnBookIds = Fixture.RentalBookFixture.getRentedBookIds()
                .stream()
                .filter(bookId -> bookId.getId() % 2 == 0)
                .toList();
        OffsetDateTime now = OffsetDateTime.now();

        //when
        rental.returnBook(returnBookIds, now);

        //then
        rental.getRentalBookList().forEach(rentalBook -> {
            if (returnBookIds.contains(rentalBook.getBookId())) {
                assertThat(rentalBook.getReturnDateTime()).isEqualTo(now);
            } else {
                assertThat(rentalBook.getReturnDateTime()).isNull();
            }
        });
    }

    @DisplayName("책 반납 - 예외(null 인 반납 책 리스트)")
    @Test
    void returnBook_exception1() {
        //given
        Rental rental = Fixture.RentalFixture.rentedRental();

        OffsetDateTime now = OffsetDateTime.now();

        //when
        Executable when = () -> rental.returnBook(null, now);

        //then
        assertThrows(RentalException.InvalidBookIdList.class, when);
    }

    @DisplayName("책 반납 - 예외(비어있는 반납 책 리스트)")
    @Test
    void returnBook_exception2() {
        //given
        Rental rental = Fixture.RentalFixture.rentedRental();

        OffsetDateTime now = OffsetDateTime.now();
        List<BookId> bookIds = List.of();

        //when
        Executable when = () -> rental.returnBook(bookIds, now);

        //then
        assertThrows(RentalException.InvalidBookIdList.class, when);
    }

    @DisplayName("책 반납 - 예외(반납 시간이 null)")
    @Test
    void returnBook_exception3() {
        //given
        Rental rental = Fixture.RentalFixture.rentedRental();

        List<BookId> returnBookIds = Fixture.RentalBookFixture.getRentedBookIds()
                .stream()
                .filter(bookId -> bookId.getId() % 2 == 0)
                .toList();

        //when
        Executable when = () -> rental.returnBook(returnBookIds, null);

        //then
        assertThrows(RentalException.InvalidReturnDateTime.class, when);
    }
}
