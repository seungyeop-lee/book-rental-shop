package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.in.web.dto;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookReadResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Data
@NoArgsConstructor
public class BookReadResponse {
    private Long id;
    private String title;
    private String isbn;

    public static BookReadResponse of(BookReadResult input) {
        return Converter.INSTANCE.convert(input);
    }

    @Mapper
    public interface Converter {
        Converter INSTANCE = getMapper(Converter.class);

        @Mapping(source = "id.id", target = "id")
        BookReadResponse convert(BookReadResult input);
    }
}
