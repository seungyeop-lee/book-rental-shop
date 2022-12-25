package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.in.web;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.in.web.dto.RentalRequest;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.in.web.dto.ReturnRequest;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.RentalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rental")
@RequiredArgsConstructor
public class RentalRestController {
    private final RentalUseCase rentalUsecase;

    @PostMapping("rental")
    public void rentalBook(@RequestBody RentalRequest rentalRequest) {
        rentalUsecase.rentalBook(rentalRequest.toCommand());
    }

    @PostMapping("return")
    public void returnBook(@RequestBody ReturnRequest rentalRequest) {
        rentalUsecase.returnBook(rentalRequest.toCommand());
    }
}
