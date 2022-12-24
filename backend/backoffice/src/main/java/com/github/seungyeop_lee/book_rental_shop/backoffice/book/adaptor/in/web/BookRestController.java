package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.in.web;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.in.web.dto.BookReadResponse;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.BookUseCase;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookCreateCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookReadResult;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookUpdateCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.BookId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookRestController {

    private final BookUseCase bookUsecase;

    @PostMapping("")
    public ResponseEntity<Long> registerBook(@RequestBody BookCreateCommand param) {
        BookId bookId = bookUsecase.registerBook(param);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookId.getId());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookReadResponse> readBook(@PathVariable Long bookId) {
        BookReadResult bookReadResult = bookUsecase.readBook(new BookId(bookId));
        return ResponseEntity.status(HttpStatus.OK).body(new BookReadResponse(bookReadResult));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(
            @PathVariable Long bookId,
            @RequestBody BookUpdateCommand param
    ) {
        bookUsecase.updateBook(new BookId(bookId), param);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        bookUsecase.deleteBook(new BookId(bookId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
