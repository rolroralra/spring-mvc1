package hello.springmvc.controller.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {
    private static final Logger log = LoggerFactory.getLogger(MappingController.class);

    @GetMapping(value = "/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return HttpStatus.OK.toString();
    }

    /**
     * @see RequestMethod
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return HttpStatus.OK.toString();
    }

    /**
     * @see org.springframework.web.bind.annotation.GetMapping
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see org.springframework.web.bind.annotation.DeleteMapping
     * @see org.springframework.web.bind.annotation.PutMapping
     * @see org.springframework.web.bind.annotation.PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return HttpStatus.OK.toString();
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPathVariable(@PathVariable(value ="userId") String userId) {
        log.info("mappingPathVariable userId={}", userId);
        return HttpStatus.OK.toString();
    }

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPathVariable userId={}, orderId={}", userId, orderId);
        return HttpStatus.OK.toString();
    }

    /**
     * RequestParameters 조건부 매핑<br/><br/>
     * <code>
     * params="mode"<br/>
     * params="!mode"<br/>
     * params="mode=debug"<br/>
     * params="mode!=debug"<br/>
     * params={"mode=debug", "data=good"}<br/>
     * </code>
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return HttpStatus.OK.toString();
    }

    /**
     * Request Header 조건부 매핑<br/><br/>
     * <code>
     *  headers="mode"<br/>
     *  headers="!mode"<br/>
     *  headers="mode=debug"<br/>
     *  headers="mode!=debug"<br/>
     *  headers={"mode=debug", "data=good"}<br/>
     * </code>
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return HttpStatus.OK.toString();
    }

    /**
     * Content-Type 조건 매핑<br/><br/>
     * <code>
     *     consumes="application/json"<br/>
     *     consumes="!application/json"<br/>
     *     consumes="application/*"<br/>
     *     consumes="*\/*"<br/>
     *     consumes=MediaType.APPLICATION_JSON_VALUE<br/>
     * </code><br/>
     *
     * <p>415 Unsupported Media Type HTTP/1.1</p>
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {

        log.info("mappingConsumes");
        return HttpStatus.OK.toString();
    }

    /**
     * Accept 조건 매핑<br/><br/>
     * <code>
     *     produces="application/json"<br/>
     *     produces="!application/json"<br/>
     *     produces="application/*"<br/>
     *     produces="*\/*"<br/>
     *     produces=MediaType.APPLICATION_JSON_VALUE<br/>
     * </code><br/>
     *
     * <p>406 Not Acceptable HTTP/1.1</p>
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_PLAIN_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return HttpStatus.OK.toString();
    }
}

