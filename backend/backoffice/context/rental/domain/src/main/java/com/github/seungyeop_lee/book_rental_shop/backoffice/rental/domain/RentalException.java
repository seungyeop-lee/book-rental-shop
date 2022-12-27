package com.github.seungyeop_lee.book_rental_shop.backoffice.rental.domain;

public class RentalException {
    public static class InvalidBookIdList extends RuntimeException {
        public InvalidBookIdList(String message) {
            super(message);
        }
    }

    public static class InvalidRentalDateTime extends RuntimeException {
        public InvalidRentalDateTime(String message) {
            super(message);
        }
    }

    public static class InvalidReturnDateTime extends RuntimeException {
        public InvalidReturnDateTime(String message) {
            super(message);
        }
    }
}
