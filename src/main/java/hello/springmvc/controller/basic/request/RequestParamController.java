package hello.springmvc.controller.basic.request;

import hello.springmvc.controller.basic.request.model.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write(HttpStatus.OK.toString());
    }

    /**
     * <p>@RequestParam 사용</p>
     * <p>Request Parameter 이름으로 Binding</p>
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username, @RequestParam("age") int age) {
        log.info("username={}, age={}", username, age);
        return HttpStatus.OK.toString();
    }

    /**
     * <p>@RequestParam 사용</p>
     * <p>HTTP Request Parameter 이름과 변수 이름이 같다면, @RequestParam.name 생략 가능</p>
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return HttpStatus.OK.toString();
    }

    /**
     * <p>@RequestParam 사용</p>
     * <p>String, int 와 같이 Primitive Type 이면, @RequestParam 생략 가능</p>
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return HttpStatus.OK.toString();
    }

    /**
     * <p>@RequestParam.required</p>
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam String username, @RequestParam(required = false) int age) {
        log.info("username={}, age={}", username, age);
        return HttpStatus.OK.toString();
    }

    /**
     * <p>@RequestParam.defaultValue</p>
     * <p>defaultValue 설정이 되어있다면, required 는 의미가 없다.</p>
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
        @RequestParam(defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return HttpStatus.OK.toString();
    }

    /**
     * <p>@RequestParam, MultiValueMap or Map</p>
     * @see MultiValueMap
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam MultiValueMap<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return HttpStatus.OK.toString();
    }

    /**
     * <p>@ModelAttribute 사용</p>
     * <p>Model 객체를 자동으로 만들어서 View에 넘겨주는 것도 자동 적용됨</p>
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.username(), helloData.age());
        return HttpStatus.OK.toString();
    }

    /**
     * <p>@ModelAttribute 생략 가능</p><br/>
     * <p>Primitive Type 의 경우, @RequestParam 자동 적용</p>
     * <p>Argument Resolver 로 지정해둔 Type 이외의 경우, @ModelAttribute 자동 적용</p>
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData hello) {
        log.info("username={}, age={}", hello.username(), hello.age());
        return HttpStatus.OK.toString();
    }
}
