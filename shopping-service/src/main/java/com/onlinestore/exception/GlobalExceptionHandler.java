package com.onlinestore.exception;

import com.onlinestore.apiresponse.*;
import feign.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> globalException(FeignException ex) {
        return new ResponseEntity<>(ex.contentUTF8(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception ex) {
        String message = ex.message;
        int id = ex.id;
        return new ResponseEntity<>(new ApiResponse(message, id), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> exception = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            exception.put(field, defaultMessage);
        });
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
