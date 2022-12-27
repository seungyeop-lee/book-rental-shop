package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.service;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto.RentalCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto.ReturnCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalFinder;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalSaver;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalUpdater;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.Rental;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.MemberId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @InjectMocks
    private RentalService rentalService;

    @Mock
    private RentalSaver rentalSaver;
    @Mock
    private RentalFinder rentalFinder;
    @Mock
    private RentalUpdater rentalUpdater;

    @DisplayName("책 대여")
    @Test
    void rentalBook() {
        //given
        List<BookId> bookIds = List.of(new BookId(100L), new BookId(200L));
        MemberId memberId = new MemberId(100L);

        RentalCommand rentalCommand = new RentalCommand();
        rentalCommand.setBookIds(bookIds);
        rentalCommand.setMemberId(memberId);

        //when
        rentalService.rentalBook(rentalCommand);

        //then
        verify(rentalSaver).save(any(Rental.class));
    }

    @DisplayName("책 반납")
    @Test
    void returnBook() {
        //given
        List<BookId> rentalBookIds = List.of(new BookId(100L), new BookId(200L));
        RentalId rentalId = new RentalId(100L);

        ReturnCommand returnCommand = new ReturnCommand();
        returnCommand.setRentalId(rentalId);
        returnCommand.setBookIds(rentalBookIds);

        Rental saved = mock(Rental.class);
        when(rentalFinder.findById(rentalId)).thenReturn(saved);

        //when
        rentalService.returnBook(returnCommand);

        //then
        verify(rentalFinder).findById(rentalId);
        verify(saved).returnBook(eq(rentalBookIds), any());
        verify(rentalUpdater).update(saved);
    }
}
