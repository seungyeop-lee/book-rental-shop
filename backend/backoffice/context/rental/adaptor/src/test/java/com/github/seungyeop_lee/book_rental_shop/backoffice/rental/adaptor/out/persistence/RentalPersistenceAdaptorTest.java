package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence;

import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence.entity.RentalJpaEntity;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.adaptor.out.persistence.entity.RentalRepository;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain.Rental;
import com.github.seungyeop_lee.book_rental_shop.backoffice.rental.vo.RentalId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalPersistenceAdaptorTest {
    @InjectMocks
    private RentalPersistenceAdaptor rentalPersistenceAdaptor;

    @Mock
    private RentalRepository rentalRepository;

    @Test
    void findById() {
        // given
        RentalJpaEntity mockJpaEntity = mock(RentalJpaEntity.class);
        when(rentalRepository.getReferenceById(Fixture.getRentalId().getId())).thenReturn(mockJpaEntity);
        Rental mockDomain = mock(Rental.class);
        when(mockJpaEntity.toDomain()).thenReturn(mockDomain);

        // when
        Rental found = rentalPersistenceAdaptor.findById(Fixture.getRentalId());

        // then
        assertThat(found).isEqualTo(mockDomain);
    }

    @Test
    void save() {
        // given
        Rental mockRental = mock(Rental.class);
        when(rentalRepository.save(any(RentalJpaEntity.class))).thenAnswer(invocation -> {
            RentalJpaEntity mockRentalJpaEntity = mock(RentalJpaEntity.class);
            when(mockRentalJpaEntity.getId()).thenReturn(Fixture.getRentalId().getId());
            return mockRentalJpaEntity;
        });

        // when
        RentalId savedRentalId = rentalPersistenceAdaptor.save(mockRental);

        // then
        verify(rentalRepository).save(any());
        assertThat(savedRentalId).isNotNull();
    }

    @Test
    void update() {
        // given
        Rental mockRental = mock(Rental.class);
        when(mockRental.getId()).thenReturn(Fixture.getRentalId());

        RentalJpaEntity mockRentalJpaEntity = mock(RentalJpaEntity.class);
        when(rentalRepository.getReferenceById(Fixture.getRentalId().getId())).thenReturn(mockRentalJpaEntity);

        // when
        rentalPersistenceAdaptor.update(mockRental);

        // then
        verify(mockRentalJpaEntity).update(mockRental);
    }
}
