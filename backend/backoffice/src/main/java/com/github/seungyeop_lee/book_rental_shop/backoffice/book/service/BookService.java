package com.github.seungyeop_lee.book_rental_shop.backoffice.book.service;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.BookRepository;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookCreateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookUpdateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.result.BookReadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Long registerBook(BookCreateParameter param) {
        Book book = param.buildBook();
        Book save = bookRepository.save(book);
        return save.getId();
    }

    public BookReadResult readBook(Long bookId) {
        Book book = bookRepository.getReferenceById(bookId);
        return BookReadResult.of(book);
    }

    public void updateBook(Long bookId, BookUpdateParameter param) {
        Book book = bookRepository.getReferenceById(bookId);
        book.update(param.buildBook());
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
