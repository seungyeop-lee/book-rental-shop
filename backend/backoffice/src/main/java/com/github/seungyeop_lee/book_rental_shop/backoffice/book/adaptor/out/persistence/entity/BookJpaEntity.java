package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.out.persistence.entity;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        this.id = book.getId().getId();
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
    }

    public void update(Book book) {
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
    }

    public Book mapToDomain() {
        return Book.builder()
                .id(new BookId(id))
                .title(title)
                .isbn(isbn)
                .build();
    }
}
