package com.example.springservlet.basic.request;

import java.util.Arrays;
import java.util.Optional;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
@Slf4j
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private void printStartLine(HttpServletRequest request) {
        log.info("--- HTTP-REQUEST-LINE - start ---");
        log.info("request.getMethod() = " + request.getMethod());
        log.info("request.getProtocol() = " + request.getProtocol());
        log.info("request.getScheme() = " + request.getScheme());
        log.info("request.getRequestURL() = " + request.getRequestURL());
        log.info("request.getRequestURI() = " + request.getRequestURI());
        log.info("request.getQueryString() = " + request.getQueryString());
        log.info("request.isSecure() = " + request.isSecure());
        log.info("--- HTTP-REQUEST-LINE - end ---");
        log.info("\n");
    }

    private void printHeaders(HttpServletRequest request) {
        log.info("--- HTTP Request Headers - start ---");
        request.getHeaderNames().asIterator()
            .forEachRemaining(headerName ->
                log.info(headerName + ": " + request.getHeader(headerName))
            );
        log.info("--- HTTP Request Headers - end ---");
        log.info("\n");
    }

    private void printHeaderUtils(HttpServletRequest request) {
        log.info("--- HTTP Request Header 편의 조회 - start ---");
        log.info("[Host 편의 조회]");
        log.info("request.getServerName() = " + request.getServerName());
        log.info("request.getServerPort() = " + request.getServerPort());
        log.info("\n");

        log.info("[Accept-Language 편의 조회]");
        request.getLocales().asIterator().forEachRemaining(locale ->
            log.info("locale = " + locale)
        );
        log.info("request.getLocale() = " + request.getLocale());
        log.info("\n");

        log.info("[Cookie 편의 조회]");
        Optional.ofNullable(request.getCookies())
            .stream()
            .flatMap(Arrays::stream)
                .forEach(cookie ->
                    log.info(cookie.getName() + ": " + cookie.getValue())
                );
        log.info("\n");

        log.info("[Content 편의 조회]");
        log.info("request.getContentType() = " + request.getContentType());
        log.info("request.getContentLength() = " + request.getContentLength());
        log.info("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        log.info("--- HTTP Request Header 편의 조회 - end ---");
        log.info("\n");
    }

    private void printEtc(HttpServletRequest request) {
        log.info("--- 기타 조회 - start ---");
        log.info("[Remote 정보]");
        log.info("request.getRemoteHost() = " + request.getRemoteHost());
        log.info("request.getRemoteAddr() = " + request.getRemoteAddr());
        log.info("request.getRemoteAddr() = " + request.getRemoteAddr());
        log.info("request.getRemotePort() = " + request.getRemotePort());
        log.info("\n");

        log.info("[Local 정보]");
        log.info("request.getLocalName() = " + request.getLocalName());
        log.info("request.getLocalAddr() = " + request.getLocalAddr());
        log.info("request.getLocalPort() = " + request.getLocalPort());
        log.info("--- 기타 조회 - end ---");
        log.info("\n");
    }
}
