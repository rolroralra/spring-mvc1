package com.example.springservlet.web.frontcontroller.v5.adapter;

import com.example.springservlet.web.frontcontroller.ModelView;
import com.example.springservlet.web.frontcontroller.v4.ControllerV4;
import com.example.springservlet.web.frontcontroller.v5.AbstractHandlerAdapter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV4HandlerAdapter extends AbstractHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response,
        Object handler) {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        return new ModelView(viewName, model);
    }
}
