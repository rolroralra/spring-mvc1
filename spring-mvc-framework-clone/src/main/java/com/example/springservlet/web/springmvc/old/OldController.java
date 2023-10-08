package com.example.springservlet.web.springmvc.old;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// Component 이름, 즉 Bean의 이름으로 URL을 매핑한다. (과거 버전 스프링 컨트롤러)
//   BeanNameUrlHandlerMapping에 의해 핸들러 매핑된다.
@Component("/springmvc/old-controller")
@Slf4j
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        log.info("OldController.handleRequest");
        log.info("HandlerMapping: BeanNameUrlHandlerMapping, HandlerAdapter: SimpleControllerHandlerAdapter");
        log.info("ViewResolver: InternalResourceViewResolver");
        return new ModelAndView("new-form");
    }
}
