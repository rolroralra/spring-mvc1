package com.example.springservlet.web.servlet;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.repository.MockMemberRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
@Slf4j
public class MemberSaveServlet extends HttpServlet {

    private final MockMemberRepository memberRepository = MockMemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        log.info("MemberSaveServlet.service");
        String username = request.getParameter("username");
        Integer age = Integer.valueOf(request.getParameter("age"));

        Member member = new Member(username, age);
        Member savedMember = memberRepository.save(member);
        log.info("savedMember = " + savedMember);

        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();

        writer.write("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "   <meta charset=\"UTF-8\">\n" +
            "</head>\n" +
            "<body>\n" +
            "200 OK\n" +
            "<ul>\n" +
            "   <li>id=" + member.getId() + "</li>\n" +
            "   <li>username=" + member.getUsername() + "</li>\n" +
            "   <li>age=" + member.getAge() + "</li>\n" +
            "<ul>\n" +
            "<a href=\"/index.html\">메인</a>\n" +
            "</body>\n" +
            "</html>\n");
    }
}
