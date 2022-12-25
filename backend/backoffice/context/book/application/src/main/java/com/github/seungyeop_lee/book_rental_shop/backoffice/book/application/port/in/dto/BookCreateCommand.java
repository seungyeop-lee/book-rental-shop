package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Data
public class BookCreateCommand {
    private String title;
    private String isbn;

    public Book mapToBook() {
        return Convert.INSTANCE.convert(this);
    }

    @Mapper
    interface Convert {
        Convert INSTANCE = getMapper(Convert.class);

        @Mapping(target = "id", ignore = true)
        Book convert(BookCreateCommand input);
    }
}
