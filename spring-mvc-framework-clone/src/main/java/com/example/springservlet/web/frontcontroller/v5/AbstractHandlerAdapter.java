package com.example.springservlet.web.frontcontroller.v5;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractHandlerAdapter implements MyHandlerAdapter {
    protected Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName ->
            paramMap.put(paramName, request.getParameter(paramName))
        );

        return paramMap;
    }
}
