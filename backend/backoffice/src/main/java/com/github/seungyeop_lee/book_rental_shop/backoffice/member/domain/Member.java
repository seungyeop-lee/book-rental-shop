package com.github.seungyeop_lee.book_rental_shop.backoffice.member.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    protected Long id;

    protected String name;

    @Column(unique = true)
    protected String phoneNumber;
}
