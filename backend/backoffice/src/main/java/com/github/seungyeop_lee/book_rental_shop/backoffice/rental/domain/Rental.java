package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "RENTAL")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENTAL_ID")
    protected Long id;

    protected Long memberId;

    @OneToMany(mappedBy = "rental")
    protected List<RentalBook> rentalBookList;
}
