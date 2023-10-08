package com.example.springservlet.web.frontcontroller.v5;

import com.example.springservlet.web.frontcontroller.ModelView;
import com.example.springservlet.web.frontcontroller.MyView;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.springservlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.springservlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.springservlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.example.springservlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.example.springservlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    public static final String VIEW_PREFIX = "/WEB-INF/views/";
    public static final String VIEW_SUFFIX = ".jsp";
    private final Map<String, Object> handlerMapping = new ConcurrentHashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMapping();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        ModelView modelView = handlerAdapter.handle(request, response, handler);

        Map<String, Object> model = modelView.getModel();

        MyView view = viewResolver(modelView.getViewName());

        view.render(model, request, response);
    }

    private void initHandlerMapping() {
        handlerMapping.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMapping.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMapping.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMapping.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMapping.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMapping.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        return handlerMapping.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        return handlerAdapters.stream()
            .filter(handlerAdapter -> handlerAdapter.supports(handler))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Cannot find handler adapter"));
    }

    private MyView viewResolver(String viewName) {
        return new MyView(VIEW_PREFIX + viewName + VIEW_SUFFIX);
    }
}
