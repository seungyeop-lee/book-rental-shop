package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence.entity;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.RentalBook;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "RENTAL_BOOK")
public class RentalBookJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENTAL_BOOK_ID")
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RENTAL_ID")
    protected RentalJpaEntity rental;

    protected Long BookId;

    protected OffsetDateTime rentalDateTime;

    protected OffsetDateTime rentalReturnDateTime;

    public RentalBookJpaEntity(RentalBook rentalBook) {
        this.id = rentalBook.getId().getId();
        this.BookId = rentalBook.getBookId().getId();
        this.rentalDateTime = rentalBook.getRentalDateTime();
        this.rentalReturnDateTime = rentalBook.getRentalReturnDateTime();
    }

    public RentalBook toDomain() {
        return RentalBook.builder()
                .bookId(new BookId(this.BookId))
                .rentalDateTime(this.rentalDateTime)
                .rentalReturnDateTime(this.rentalReturnDateTime)
                .build();
    }

    public void update(RentalBook rentalBook) {
        this.BookId = rentalBook.getBookId().getId();
        this.rentalDateTime = rentalBook.getRentalDateTime();
        this.rentalReturnDateTime = rentalBook.getRentalReturnDateTime();
    }
}
