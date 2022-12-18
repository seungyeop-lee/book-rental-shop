package com.github.seungyeop_lee.book_rental_shop.backoffice.book.controller;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.BookService;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookCreateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookUpdateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.result.BookReadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<Long> registerBook(@RequestBody BookCreateParameter param) {
        Long bookId = bookService.registerBook(param);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookId);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookReadResult> readBook(@PathVariable Long bookId) {
        BookReadResult bookReadResult = bookService.readBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(bookReadResult);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(
            @PathVariable Long bookId,
            @RequestBody BookUpdateParameter param
    ) {
        bookService.updateBook(bookId, param);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
