package com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.service;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookCreateCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookReadResult;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookUpdateCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookDeleter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookFinder;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookSaver;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.out.BookUpdater;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.domain.Book;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookSaver bookSaver;
    @Mock
    private BookFinder bookFinder;
    @Mock
    private BookUpdater bookUpdater;
    @Mock
    private BookDeleter bookDeleter;

    @Mock
    private Book book;

    @DisplayName("책 등록")
    @Test
    void registerBook() {
        //given
        BookId expectBookId = new BookId(100L);
        when(bookSaver.save(any())).thenReturn(expectBookId);

        //when
        BookId bookId = bookService.registerBook(new BookCreateCommand() {{
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
        BookId bookId = new BookId(100L);
        String title = "a";
        String isbn = "b";

        when(book.getId()).thenReturn(bookId);
        when(book.getTitle()).thenReturn(title);
        when(book.getIsbn()).thenReturn(isbn);
        when(bookFinder.findById(bookId)).thenReturn(book);

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
        BookId bookId = new BookId(100L);

        //when
        bookService.updateBook(bookId, new BookUpdateCommand() {{
            setTitle("b");
            setIsbn("bcde2345");
        }});

        //then
        verify(bookUpdater).update(eq(bookId), any());
    }

    @DisplayName("책 삭제")
    @Test
    void deleteBook() {
        //given
        BookId bookId = new BookId(100L);

        //when
        bookService.deleteBook(bookId);

        //then
        verify(bookDeleter).deleteById(bookId);
    }
}
