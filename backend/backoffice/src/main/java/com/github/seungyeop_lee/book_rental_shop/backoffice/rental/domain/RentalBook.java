package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Entity
@Table(name = "RENTAL_BOOK")
public class RentalBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENTAL_BOOK_ID")
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RENTAL_ID")
    protected Rental rental;

    protected Long BookId;


    protected OffsetDateTime rentalDateTime;
}
