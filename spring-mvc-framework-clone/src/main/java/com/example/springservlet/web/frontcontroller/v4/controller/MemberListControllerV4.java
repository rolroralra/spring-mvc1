package com.example.springservlet.web.frontcontroller.v4.controller;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.repository.MockMemberRepository;
import com.example.springservlet.web.frontcontroller.v4.ControllerV4;
import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private final MockMemberRepository memberRepository = MockMemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        model.put("members", members);

        return "members";
    }
}
