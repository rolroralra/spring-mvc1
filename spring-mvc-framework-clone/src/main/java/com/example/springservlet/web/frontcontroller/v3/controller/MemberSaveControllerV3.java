package com.example.springservlet.web.frontcontroller.v3.controller;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.repository.MockMemberRepository;
import com.example.springservlet.web.frontcontroller.ModelView;
import com.example.springservlet.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private final MockMemberRepository memberRepository = MockMemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);

        return new ModelView("save-result", Map.of("member", savedMember));
    }
}
