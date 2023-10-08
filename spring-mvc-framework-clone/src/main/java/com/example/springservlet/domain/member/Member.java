package com.example.springservlet.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    private Long id;

    private String username;

    private Integer age;

    public Member(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
