package app.springboot.blog.Exceptions;

import app.springboot.blog.Payloads.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();

            errorsMap.put(fieldName, message);
        });
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> handleApiException(APIException ex) {
        String message = ex.getMessage();
        APIResponse apiResponse = new APIResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
//        Map<String, String> errorsMap = new HashMap<>();
//        ex.getConstraintViolations().forEach(error -> {
//            String message = error.getMessage();
//            String field = error.getMessageTemplate();
//
//            errorsMap.put(field,message);
//
//        });
//        return new ResponseEntity<>(errorsMap, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}

