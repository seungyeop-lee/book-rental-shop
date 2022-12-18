package com.github.seungyeop_lee.book_rental_shop.backoffice.book.service;

import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookCreateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookUpdateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.result.BookReadResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    // fixture
    private BookCreateParameter createParam;
    private BookUpdateParameter updateParam;

    @BeforeEach
    void setUp() {
        createParam = new BookCreateParameter() {{
            setTitle("a");
            setIsbn("abcd1234");
        }};
        updateParam = new BookUpdateParameter() {{
            setTitle("b");
            setIsbn("bcde2345");
        }};
    }

    @DisplayName("책 등록")
    @Test
    @Transactional
    void registerBook() {
        //when
        Long bookId = bookService.registerBook(createParam);

        //then
        assertThat(bookId).isNotNull();
    }

    @DisplayName("책 조회")
    @Test
    @Transactional
    void readBook() {
        //given
        Long bookId = bookService.registerBook(createParam);

        //when
        BookReadResult result = bookService.readBook(bookId);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo(createParam.getTitle());
        assertThat(result.getIsbn()).isEqualTo(createParam.getIsbn());
    }

    @DisplayName("책 정보 수정")
    @Test
    @Transactional
    void updateBook() {
        //given
        Long bookId = bookService.registerBook(createParam);

        //when
        bookService.updateBook(bookId, updateParam);

        //then
        BookReadResult result = bookService.readBook(bookId);
        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo(updateParam.getTitle());
        assertThat(result.getIsbn()).isEqualTo(updateParam.getIsbn());
    }

    @DisplayName("책 삭제")
    @Test
    @Transactional
    void deleteBook() {
        //given
        Long bookId = bookService.registerBook(createParam);

        //when
        bookService.deleteBook(bookId);
        
        //then
        assertThrows(JpaObjectRetrievalFailureException.class, () -> bookService.readBook(bookId));
    }
}
