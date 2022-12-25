package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.in.web.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto.ReturnCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReturnRequest {
    private Long rentalId;
    private List<Long> bookIds;

    public ReturnCommand toCommand() {
        ReturnCommand result = new ReturnCommand();
        result.setRentalId(new RentalId(rentalId));
        result.setBookIds(
                bookIds.stream()
                        .map(BookId::new)
                        .collect(Collectors.toList())
        );
        return result;
    }
}
