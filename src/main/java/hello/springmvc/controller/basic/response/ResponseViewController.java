package hello.springmvc.controller.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @GetMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        return new ModelAndView("response/hello")
            .addObject("data", "hello!");
    }

    /**
     * <code>@Controller</code> 를 사용하고, <code>String</code> 을 반환하면<br/>
     * <code>viewResolver</code> 가 화면을 찾아서 처리한다.
     */
    @GetMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");

        return "response/hello";
    }

    /**
     * <code>@Controller</code> 를 사용하고, <code>void</code> 를 반환하면<br/>
     * 요청 URL 과 같은 이름의 뷰를 찾고, 렌더링 한다.
     * @deprecated 이 방식은 명시성이 너무 떨어지고, 이렇게 딱 맞는 경우도 많이 없어서, 권장하지 않는다.
     */
    @Deprecated(forRemoval = true)
    @GetMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
