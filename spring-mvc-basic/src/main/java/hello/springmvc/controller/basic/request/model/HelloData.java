package hello.springmvc.controller.basic.request.model;

import lombok.Builder;

@Builder
public record HelloData(
    String username,
    Integer age
) {

}
