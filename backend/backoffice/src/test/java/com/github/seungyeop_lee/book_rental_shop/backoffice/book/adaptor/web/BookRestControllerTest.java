package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.BookService;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookCreateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.param.BookUpdateParameter;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.service.result.BookReadResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
class BookRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @DisplayName("[POST] 책 등록")
    @Test
    void registerBook() throws Exception {
        //given
        BookCreateParameter createParam = new BookCreateParameter() {{
            setTitle("a");
            setIsbn("b");
        }};
        when(bookService.registerBook(createParam)).thenReturn(1L);

        //when
        MockHttpServletRequestBuilder request = post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(createParam));
        ResultActions perform = mockMvc.perform(request);

        //then
        perform.andExpect(status().isCreated())
                .andExpect(content().string("1"))
                .andDo(print());
    }

    @DisplayName("[GET] 책 조회")
    @Test
    void readBook() throws Exception {
        //given
        Long bookId = 100L;
        BookReadResult readResultExpect = new BookReadResult() {{
            setId(bookId);
            setTitle("a");
            setIsbn("b");
        }};
        when(bookService.readBook(bookId)).thenReturn(readResultExpect);

        //when
        MockHttpServletRequestBuilder request = get("/book/" + bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        ResultActions perform = mockMvc.perform(request);

        //then
        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        BookReadResult result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                BookReadResult.class
        );
        assertThat(result).isEqualTo(readResultExpect);
    }

    @DisplayName("[PUT] 책 정보 수정")
    @Test
    void updateBook() throws Exception {
        //given
        Long bookId = 200L;
        BookUpdateParameter updateParam = new BookUpdateParameter() {{
            setTitle("c");
            setIsbn("d");
        }};

        //when
        MockHttpServletRequestBuilder request = put("/book/" + bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(updateParam));
        ResultActions perform = mockMvc.perform(request);

        //then
        perform.andExpect(status().isNoContent())
                .andDo(print());

        verify(bookService).updateBook(bookId, updateParam);
    }

    @DisplayName("[DELETE] 책 삭제")
    @Test
    void deleteBook() throws Exception {
        //given
        Long bookId = 300L;

        //when
        MockHttpServletRequestBuilder request = delete("/book/" + bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        ResultActions perform = mockMvc.perform(request);

        //then
        perform.andExpect(status().isNoContent())
                .andDo(print());

        verify(bookService).deleteBook(bookId);
    }
}
