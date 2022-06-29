package com.dev.blog.handler;

import com.dev.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    private ResponseDto<String> handleArgumentException(Exception e) {
        return new ResponseDto<String>(HttpStatus.OK.value(), e.getMessage());
    }
}
