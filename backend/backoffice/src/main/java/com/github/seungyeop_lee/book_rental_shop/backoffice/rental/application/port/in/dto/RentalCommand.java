package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.in.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.member.vo.MemberId;
import lombok.Data;

import java.util.List;

@Data
public class RentalCommand {
    private MemberId memberId;
    private List<BookId> bookIds;
}
