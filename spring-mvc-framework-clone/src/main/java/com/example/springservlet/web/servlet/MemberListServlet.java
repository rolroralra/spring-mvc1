package com.example.springservlet.web.servlet;

import com.example.springservlet.domain.member.Member;
import com.example.springservlet.repository.MockMemberRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
@Slf4j
public class MemberListServlet extends HttpServlet {

    private final MockMemberRepository memberRepository = MockMemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        log.info("MemberListServlet.service");

        List<Member> members = memberRepository.findAll();

        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("   <meta charset=\"UTF-8\">");
        writer.println("   <title>Title</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<a href=\"/index.html\">메인</a>");

        writer.println("<table>");
        writer.println("    <thead>");
        writer.println("    <th>id</th>");
        writer.println("    <th>username</th>");
        writer.println("    <th>age</th>");
        writer.println("    </thead>");
        writer.println("    <tbody>");

        for (Member member : members) {
            writer.println("    <tr>");
            writer.println("        <td>" + member.getId() + "</td>");
            writer.println("        <td>" + member.getUsername() + "</td>");
            writer.println("        <td>" + member.getAge() + "</td>");
            writer.println("    </tr>");
        }

        writer.println("    </tbody>");
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
