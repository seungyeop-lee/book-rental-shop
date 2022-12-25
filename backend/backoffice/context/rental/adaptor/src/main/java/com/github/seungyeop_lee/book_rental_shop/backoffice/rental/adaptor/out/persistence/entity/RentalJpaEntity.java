package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence.entity;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.Rental;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.RentalBook;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.MemberId;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "RENTAL")
public class RentalJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENTAL_ID")
    protected Long id;

    protected Long memberId;

    @OneToMany(mappedBy = "rental")
    protected List<RentalBookJpaEntity> rentalBookList;

    public RentalJpaEntity(Rental rental) {
        this.id = rental.getId().getId();
        this.memberId = rental.getMemberId().getId();
        this.rentalBookList = rental.getRentalBookList()
                .stream()
                .map(RentalBookJpaEntity::new)
                .collect(Collectors.toList());
    }

    public Rental toDomain() {
        return Rental.builder()
                .id(new RentalId(this.id))
                .memberId(new MemberId(this.memberId))
                .rentalBookList(this.rentalBookList.stream().map(RentalBookJpaEntity::toDomain).collect(Collectors.toList()))
                .build();
    }

    public void update(Rental rental) {
        this.memberId = rental.getMemberId().getId();

        Map<Long, RentalBookJpaEntity> savedById = this.rentalBookList
                .stream()
                .collect(Collectors.toMap(
                        RentalBookJpaEntity::getBookId,
                        rentalBookJpaEntity -> rentalBookJpaEntity
                ));
        Map<Long, RentalBook> updateById = rental.getRentalBookList()
                .stream()
                .collect(Collectors.toMap(
                        rentalBook -> rentalBook.getBookId().getId(),
                        rentalBook -> rentalBook
                ));
        updateById.forEach(
                (bookId, rentalBook) -> Optional
                        .ofNullable(savedById.get(bookId))
                        .ifPresent(rentalBookJpaEntity -> rentalBookJpaEntity.update(rentalBook))
        );
    }
}
