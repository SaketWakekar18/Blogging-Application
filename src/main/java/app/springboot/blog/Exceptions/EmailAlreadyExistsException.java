package app.springboot.blog.Exceptions;

import lombok.Data;

@Data
public class EmailAlreadyExistsException extends RuntimeException {
    private String message;

    public EmailAlreadyExistsException(String message) {
        this.message = message;
    }
}
