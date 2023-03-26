package app.springboot.blog.Exceptions;

public class APIException extends RuntimeException {
    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }
}
