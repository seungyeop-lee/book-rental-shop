package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.out.persistence.entity;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID")
    protected Long id;

    protected String title;

    @Column(unique = true)
    protected String isbn;

    public BookJpaEntity(Book book) {
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
    }

    public void update(Book book) {
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
    }

    public Book mapToDomain() {
        return Converter.INSTANCE.convert(this);
    }

    @Mapper
    interface Converter {
        Converter INSTANCE = Mappers.getMapper(Converter.class);

        @Mapping(source = "id", target = "id.id")
        Book convert(BookJpaEntity input);
    }
}
