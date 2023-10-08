package com.example.springservlet.web.frontcontroller.v3.controller;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.repository.MockMemberRepository;
import com.example.springservlet.web.frontcontroller.ModelView;
import com.example.springservlet.web.frontcontroller.v3.ControllerV3;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private final MockMemberRepository memberRepository = MockMemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();

        return new ModelView("members", Map.of("members", members));
    }
}
