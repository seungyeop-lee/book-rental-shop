package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
import lombok.Data;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Data
public class BookReadResult {
    private BookId id;
    private String title;
    private String isbn;

    public static BookReadResult of(Book book) {
        return Convert.INSTANCE.convert(book);
    }

    @Mapper
    interface Convert {
        Convert INSTANCE = getMapper(Convert.class);

        BookReadResult convert(Book input);
    }
}
