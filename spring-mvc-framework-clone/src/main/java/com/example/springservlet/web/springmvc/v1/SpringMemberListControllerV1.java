package com.example.springservlet.web.springmvc.v1;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.repository.MockMemberRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberListControllerV1 {
    private final MockMemberRepository memberRepository = MockMemberRepository.getInstance();

    @GetMapping("/springmvc/v1/members")
    public ModelAndView process() {
        List<Member> members = memberRepository.findAll();

        return new ModelAndView("members", "members", members);
    }
}
