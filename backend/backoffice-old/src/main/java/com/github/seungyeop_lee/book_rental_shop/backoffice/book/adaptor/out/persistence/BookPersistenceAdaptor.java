package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.out.persistence;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.out.persistence.entity.BookJpaEntity;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.out.persistence.entity.BookJpaRepository;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookDeleter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookFinder;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookSaver;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookUpdater;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookPersistenceAdaptor implements BookSaver, BookFinder, BookUpdater, BookDeleter {

    private final BookJpaRepository bookJpaRepository;

    @Override
    public BookId save(Book book) {
        BookJpaEntity saved = bookJpaRepository.save(new BookJpaEntity(book));
        return new BookId(saved.getId());
    }

    @Override
    public Book findById(BookId bookId) {
        BookJpaEntity found = bookJpaRepository.getReferenceById(bookId.getId());
        return found.mapToDomain();
    }

    @Override
    public void update(BookId bookId, Book book) {
        BookJpaEntity found = bookJpaRepository.getReferenceById(bookId.getId());
        found.update(book);
    }

    @Override
    public void deleteById(BookId bookId) {
        bookJpaRepository.deleteById(bookId.getId());
    }
}
