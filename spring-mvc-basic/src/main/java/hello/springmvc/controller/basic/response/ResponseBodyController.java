package hello.springmvc.controller.basic.response;

import hello.springmvc.controller.basic.request.model.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyStringV1(HttpServletResponse response)
        throws IOException {
        log.info("responseBodyStringV1");

        response.getWriter().write(HttpStatus.OK.toString());
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyStringV2() {
        log.info("responseBodyStringV2");

        return ResponseEntity.ok(HttpStatus.OK.toString());
    }

    @GetMapping("/response-body-string-v3")
    @ResponseStatus(HttpStatus.OK)
    public String responseBodyStringV3() {
        log.info("responseBodyStringV3");

        return HttpStatus.OK.toString();
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        log.info("responseBodyJsonV1");

        HelloData helloData = HelloData.builder()
            .username("rolroralra")
            .age(35)
            .build();

        return ResponseEntity.ok(helloData);
    }

    @GetMapping("/response-body-json-v2")
    @ResponseStatus(HttpStatus.OK)
    public HelloData responseBodyJsonV2() {
        log.info("responseBodyJsonV2");

        return HelloData.builder()
            .username("rolroralra")
            .age(35)
            .build();
    }
}
