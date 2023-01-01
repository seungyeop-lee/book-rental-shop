package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.service;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.RentalUseCase;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto.RentalCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto.ReturnCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalFinder;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalSaver;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalUpdater;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.Rental;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class RentalService implements RentalUseCase {

    private final RentalSaver rentalSaver;
    private final RentalFinder rentalFinder;
    private final RentalUpdater rentalUpdater;

    @Override
    public RentalId rentalBook(RentalCommand rentalCommand) {
        Rental rental = new Rental(rentalCommand.getMemberId());
        rental.rentBook(rentalCommand.getBookIds(), OffsetDateTime.now());
        return rentalSaver.save(rental);
    }

    @Override
    public void returnBook(ReturnCommand returnCommand) {
        Rental rental = rentalFinder.findById(returnCommand.getRentalId());
        rental.returnBook(returnCommand.getBookIds(), OffsetDateTime.now());
        rentalUpdater.update(rental);
    }
}
