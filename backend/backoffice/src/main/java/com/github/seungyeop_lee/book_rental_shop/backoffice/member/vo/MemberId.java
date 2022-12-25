package com.github.seungyeop_lee.book_rental_shop.backoffice.member.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class MemberId {
    private final Long id;

    public MemberId(Long id) {
        this.id = id;
    }
}
