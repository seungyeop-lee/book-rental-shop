package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.service;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.BookUseCase;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.param.BookCreateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.param.BookUpdateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.result.BookReadResult;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookDeleter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookFinder;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookSaver;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookUpdater;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.BookId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class BookService implements BookUseCase {

    private final BookSaver bookSaver;
    private final BookFinder bookFinder;
    private final BookUpdater bookUpdater;
    private final BookDeleter bookDeleter;

    @Override
    public BookId registerBook(BookCreateParameter param) {
        Book book = param.mapToBook();
        return bookSaver.save(book);
    }

    @Override
    public BookReadResult readBook(BookId bookId) {
        Book book = bookFinder.findById(bookId);
        return BookReadResult.of(book);
    }

    @Override
    public void updateBook(BookId bookId, BookUpdateParameter param) {
        Book book = param.mapToBook();
        bookUpdater.update(bookId, book);
    }

    @Override
    public void deleteBook(BookId bookId) {
        bookDeleter.deleteById(bookId);
    }
}
