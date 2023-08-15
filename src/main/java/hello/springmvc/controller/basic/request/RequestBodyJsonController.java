package hello.springmvc.controller.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.controller.basic.request.model.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RequestBodyJsonController {

    private final ObjectMapper objectMapper;

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        HelloData helloData = objectMapper.readValue(request.getInputStream(), HelloData.class);

        log.info("helloData={}", helloData);

        response.getWriter().write(HttpStatus.OK.toString());
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody)
        throws JsonProcessingException {
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("helloData={}", helloData);

        return HttpStatus.OK.toString();
    }

    /**
     * <code>HttpEntity<T></code>, <code>@RequestBody</code> 를 사용하면<br/>
     * <code>HttpMessageConverter</code>가 HTTP 메시지 바디의 내용을<br/>우리가 원하는 문자나 객체 등으로 변환해준다.
     *
     * @see org.springframework.http.converter.HttpMessageConverter
     * @see org.springframework.http.converter.StringHttpMessageConverter
     * @see org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        log.info("helloData={}", helloData);

        return HttpStatus.OK.toString();
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(@RequestBody HttpEntity<HelloData> httpEntity) {
        HelloData helloData = httpEntity.getBody();
        log.info("helloData={}", helloData);

        return HttpStatus.OK.toString();
    }

    /**
     * <code>@ResponseBody</code> 를 사용하면<br/>
     * <code>HttpMessageConverter</code>가 HttpResponseMessage 바디의 내용을<br/>
     * 우리가 원하는 문자나 객체 등으로 변환해준다.
     *
     * @see org.springframework.http.converter.HttpMessageConverter
     * @see org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) {
        log.info("helloData={}", helloData);

        return helloData;
    }

    @ResponseBody
    @PostMapping("/request-body-json-v6")
    public ResponseEntity<HelloData> requestBodyJsonV6(@RequestBody HelloData helloData) {
        log.info("helloData={}", helloData);

        return ResponseEntity.ok(helloData);
    }
}
