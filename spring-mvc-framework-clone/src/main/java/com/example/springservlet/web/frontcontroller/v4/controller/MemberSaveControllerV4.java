package com.example.springservlet.web.frontcontroller.v4.controller;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.repository.MockMemberRepository;
import com.example.springservlet.web.frontcontroller.v4.ControllerV4;
import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private final MockMemberRepository memberRepository = MockMemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);

        model.put("member", savedMember);

        return "save-result";
    }
}
