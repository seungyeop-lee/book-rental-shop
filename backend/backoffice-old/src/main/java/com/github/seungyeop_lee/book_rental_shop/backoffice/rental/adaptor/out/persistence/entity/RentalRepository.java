package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalJpaEntity, Long> {
}
