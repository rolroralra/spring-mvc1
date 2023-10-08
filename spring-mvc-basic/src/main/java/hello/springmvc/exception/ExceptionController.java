package hello.springmvc.exception;

//@RestControllerAdvice
public class ExceptionController {

    public String handleException(Exception exception) {
        return exception.getMessage();
    }
}
