package hello.springmvc.controller.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        String requestMessageBody = StreamUtils.copyToString(
            request.getInputStream(), StandardCharsets.UTF_8
        );

        log.info("requestPayload={}", requestMessageBody);

        response.getWriter().write(HttpStatus.OK.toString());
    }

    /**
     * @param inputStream HTTP 요청 메시지 바디의 내용을 직접 조회 (SpringMVC 에서 제공하는 기능)
     * @param outputStream HTTP 응답 메시지 바디에 직접 결과 출력 (SpringMVC 에서 제공하는 기능)
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer outputStream)
        throws IOException {
        String requestMessageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("requestPayload={}", requestMessageBody);

        outputStream.write(HttpStatus.OK.toString());
    }

    /**
     * @param httpEntity HTTP Header, Body 정보를 편리하게 조회 (SpringMVC 에서 제공하는 기능)
     *
     * @see org.springframework.http.converter.HttpMessageConverter
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String requestMessageBody = httpEntity.getBody();

        log.info("requestPayload={}", requestMessageBody);

        return new HttpEntity<>(HttpStatus.OK.toString(), new LinkedMultiValueMap<>());
    }

    /**
     * <code>@RequestBody</code><br>
     * <p>HTTP Request Message Body 를 직접 조회</p>
     * <br/>
     * <code>@ResponseBody</code>
     * <p>HTTP Response Message Body 를 직접 반환</p>
     * <p>View 조회를 무시하고, HTTP Message Converter 를 통해 HTTP Message Body 에 직접 해당 내용 입력</p>
     * @see org.springframework.http.converter.HttpMessageConverter
     * @see ResponseBody
     * @see RequestBody
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        log.info("requestPayload={}", messageBody);
        return HttpStatus.OK.toString();
    }
}
