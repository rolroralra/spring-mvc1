package com.example.springservlet.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.springservlet.domain.member.Member;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MockMemberRepositoryTest {
    private final MockMemberRepository memberRepository = MockMemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }

    @Test
    void save() {
        // Given
        Member member = new Member("hello", 20);

        // When
        Member savedMember = memberRepository.save(member);

        // Then
        Optional<Member> findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember)
            .isPresent()
            .get()
            .isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        // Given
        Member member1 = new Member("hello1", 10);
        Member member2 = new Member("hello2", 20);

        // When
        memberRepository.save(member1);
        memberRepository.save(member2);

        // Then
        List<Member> result = memberRepository.findAll();
        assertThat(result)
            .hasSize(2)
            .containsOnly(member1, member2);
    }
}
