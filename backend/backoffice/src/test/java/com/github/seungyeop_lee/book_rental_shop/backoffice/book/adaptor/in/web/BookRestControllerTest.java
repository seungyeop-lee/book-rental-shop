package com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.adaptor.in.web.dto.BookReadResponse;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.BookUseCase;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookCreateCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookReadResult;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.application.port.in.dto.BookUpdateCommand;
import com.github.seungyeop_lee.book_rental_shop.backoffice.book.vo.BookId;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
class BookRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookUseCase bookUsecase;

    @DisplayName("[POST] 책 등록")
    @Test
    void registerBook() throws Exception {
        //given
        BookCreateCommand createParam = new BookCreateCommand() {{
            setTitle("a");
            setIsbn("b");
        }};
        BookId expectBookId = new BookId(1L);
        when(bookUsecase.registerBook(createParam)).thenReturn(expectBookId);

        //when
        MockHttpServletRequestBuilder request = post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(createParam));
        ResultActions perform = mockMvc.perform(request);

        //then
        MvcResult mvcResult = perform.andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        BookId result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookId.class);
        assertThat(result).isEqualTo(expectBookId);
    }

    @DisplayName("[GET] 책 조회")
    @Test
    void readBook() throws Exception {
        //given
        BookId bookId = new BookId(100L);
        BookReadResult readResultExpect = new BookReadResult() {{
            setId(bookId);
            setTitle("a");
            setIsbn("b");
        }};
        when(bookUsecase.readBook(bookId)).thenReturn(readResultExpect);

        //when
        MockHttpServletRequestBuilder request = get("/book/" + bookId.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        ResultActions perform = mockMvc.perform(request);

        //then
        MvcResult mvcResult = perform.andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        BookReadResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                BookReadResponse.class
        );
        assertThat(result).isEqualTo(new BookReadResponse(readResultExpect));
    }

    @DisplayName("[PUT] 책 정보 수정")
    @Test
    void updateBook() throws Exception {
        //given
        BookId bookId = new BookId(200L);
        BookUpdateCommand updateParam = new BookUpdateCommand() {{
            setTitle("c");
            setIsbn("d");
        }};

        //when
        MockHttpServletRequestBuilder request = put("/book/" + bookId.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(updateParam));
        ResultActions perform = mockMvc.perform(request);

        //then
        perform.andExpect(status().isNoContent())
                .andDo(print());

        verify(bookUsecase).updateBook(bookId, updateParam);
    }

    @DisplayName("[DELETE] 책 삭제")
    @Test
    void deleteBook() throws Exception {
        //given
        BookId bookId = new BookId(300L);

        //when
        MockHttpServletRequestBuilder request = delete("/book/" + bookId.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        ResultActions perform = mockMvc.perform(request);

        //then
        perform.andExpect(status().isNoContent())
                .andDo(print());

        verify(bookUsecase).deleteBook(bookId);
    }
}
