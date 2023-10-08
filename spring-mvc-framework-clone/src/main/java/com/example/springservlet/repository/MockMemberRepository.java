package com.example.springservlet.repository;

import com.example.springservlet.domain.member.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MockMemberRepository {
    private static final Map<Long, Member> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L);

    private static class LazyHolder {
        private static final MockMemberRepository INSTANCE = new MockMemberRepository();
    }

    public static MockMemberRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    public Member save(Member member) {
        member.setId(sequence.addAndGet(1L));
        store.put(member.getId(), member);

        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
        sequence.set(0L);
    }
}
