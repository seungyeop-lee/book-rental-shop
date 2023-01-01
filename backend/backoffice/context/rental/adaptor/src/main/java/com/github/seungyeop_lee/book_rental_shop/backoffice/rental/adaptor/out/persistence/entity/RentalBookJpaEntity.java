package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence.entity;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.RentalBook;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.BookId;
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

    protected Long bookId;

    protected OffsetDateTime rentalDateTime;

    protected OffsetDateTime returnDateTime;

    public RentalBookJpaEntity(RentalBook rentalBook) {
        this.id = rentalBook.getId().getId();
        this.bookId = rentalBook.getBookId().getId();
        this.rentalDateTime = rentalBook.getRentalDateTime();
        this.returnDateTime = rentalBook.getReturnDateTime();
    }

    public RentalBook toDomain() {
        return RentalBook.builder()
                .bookId(new BookId(this.bookId))
                .rentalDateTime(this.rentalDateTime)
                .returnDateTime(this.returnDateTime)
                .build();
    }

    public void update(RentalBook rentalBook) {
        this.bookId = rentalBook.getBookId().getId();
        this.rentalDateTime = rentalBook.getRentalDateTime();
        this.returnDateTime = rentalBook.getReturnDateTime();
    }
}
