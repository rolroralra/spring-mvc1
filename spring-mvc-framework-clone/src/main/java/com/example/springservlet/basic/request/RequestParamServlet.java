package com.example.springservlet.basic.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
@Slf4j
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        log.info("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator().forEachRemaining(paramName ->
            log.info("request.getParameter(\"" + paramName + "\") = " + request.getParameter(paramName))
        );
        log.info("[전체 파라미터 조회] - end");
        log.info("\n");

        log.info("[단일 파라미터 조회] - start");
        log.info(
            "request.getParameter(\"username\") = " + request.getParameter("username"));
        log.info(
            "request.getParameter(\"age\") = " + request.getParameter("age")
        );
        log.info("[단일 파라미터 조회] - end");
        log.info("\n");

        log.info("[이름이 같은 복수 파라미터 조회] - start");
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            log.info("username = " + username);
        }
        log.info("[이름이 같은 복수  파라미터 조회] - end");
        log.info("\n");

        response.getWriter().write(HttpStatus.OK.toString());
    }
}
