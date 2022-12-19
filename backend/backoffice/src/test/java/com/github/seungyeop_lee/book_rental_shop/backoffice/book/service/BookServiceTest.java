package com.github.seungyeop_lee.book_rental_shop.backoffice.book.service;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.BookRepository;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookCreateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookUpdateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.result.BookReadResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Book book;

    @DisplayName("책 등록")
    @Test
    void registerBook() {
        //given
        Long expectBookId = 100L;
        when(book.getId()).thenReturn(expectBookId);
        when(bookRepository.save(any())).thenReturn(book);

        //when
        Long bookId = bookService.registerBook(new BookCreateParameter() {{
            setTitle("a");
            setIsbn("abcd1234");
        }});

        //then
        assertThat(bookId).isEqualTo(expectBookId);
    }

    @DisplayName("책 조회")
    @Test
    void readBook() {
        //given
        Long bookId = 100L;
        String title = "a";
        String isbn = "b";

        when(book.getId()).thenReturn(bookId);
        when(book.getTitle()).thenReturn(title);
        when(book.getIsbn()).thenReturn(isbn);
        when(bookRepository.getReferenceById(bookId)).thenReturn(book);

        //when
        BookReadResult result = bookService.readBook(bookId);

        //then
        assertThat(result.getId()).isEqualTo(bookId);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getIsbn()).isEqualTo(isbn);
    }

    @DisplayName("책 정보 수정")
    @Test
    void updateBook() {
        //given
        Long bookId = 100L;
        when(bookRepository.getReferenceById(bookId)).thenReturn(book);

        //when
        bookService.updateBook(bookId, new BookUpdateParameter() {{
            setTitle("b");
            setIsbn("bcde2345");
        }});

        //then
        verify(book).update(any());
    }

    @DisplayName("책 삭제")
    @Test
    void deleteBook() {
        //given
        Long bookId = 100L;

        //when
        bookService.deleteBook(bookId);

        //then
        verify(bookRepository).deleteById(bookId);
    }
}
