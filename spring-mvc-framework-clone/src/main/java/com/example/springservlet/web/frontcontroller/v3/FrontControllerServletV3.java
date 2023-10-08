package com.example.springservlet.web.frontcontroller.v3;

import com.example.springservlet.web.frontcontroller.ModelView;
import com.example.springservlet.web.frontcontroller.MyView;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.springservlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    public static final String VIEW_PREFIX = "/WEB-INF/views/";
    public static final String VIEW_SUFFIX = ".jsp";
    private final Map<String, ControllerV3> controllerMap = new ConcurrentHashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = getParamMap(request);
        ModelView modelAndView = controller.process(paramMap);

        String viewName = modelAndView.getViewName();
        MyView view = viewResolver(viewName);
        Map<String, Object> model = modelAndView.getModel();

        view.render(model, request, response);
    }

    private Map<String, String> getParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName ->
            paramMap.put(paramName, request.getParameter(paramName))
        );

        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView(VIEW_PREFIX + viewName + VIEW_SUFFIX);
    }
}
