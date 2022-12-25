package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence.entity.RentalJpaEntity;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence.entity.RentalRepository;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalFinder;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalSaver;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.application.port.out.RentalUpdater;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.Rental;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RentalPersistenceAdaptor implements RentalSaver, RentalFinder, RentalUpdater {

    private final RentalRepository rentalRepository;

    @Override
    public Rental findById(RentalId id) {
        RentalJpaEntity found = rentalRepository.getReferenceById(id.getId());
        return found.toDomain();
    }

    @Override
    public void save(Rental rental) {
        RentalJpaEntity entity = new RentalJpaEntity(rental);
        rentalRepository.save(entity);
    }

    @Override
    public void update(Rental rental) {
        RentalJpaEntity found = rentalRepository.getReferenceById(rental.getId().getId());
        found.update(rental);
    }
}
