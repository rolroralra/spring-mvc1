package com.example.springservlet.web.springmvc.old;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

//   BeanNameUrlHandlerMapping 에 의해 핸들러 매핑된다.
@Component("/springmvc/request-handler")
@Slf4j
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        log.info("MyHttpRequestHandler.handleRequest");
        log.info("HandlerMapping: BeanNameUrlHandlerMapping, HandlerAdapter: HttpRequestHandlerAdapter");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(
            "/WEB-INF/views/new-form.jsp");

        requestDispatcher.forward(request, response);
    }
}
