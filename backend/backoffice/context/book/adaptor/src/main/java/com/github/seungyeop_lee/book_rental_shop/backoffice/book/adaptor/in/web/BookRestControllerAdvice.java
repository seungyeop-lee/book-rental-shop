package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.in.web;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookRestControllerAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> handleIllegalArgumentException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
