package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.in.web.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto.RentalCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.MemberId;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RentalRequest {
    private Long memberId;
    private List<Long> bookIds;

    public RentalCommand toCommand() {
        RentalCommand result = new RentalCommand();
        result.setMemberId(new MemberId(memberId));
        result.setBookIds(
                bookIds.stream()
                        .map(BookId::new)
                        .collect(Collectors.toList())
        );
        return result;
    }
}
