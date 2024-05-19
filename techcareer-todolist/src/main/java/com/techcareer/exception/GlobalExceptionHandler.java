package com.techcareer.exception;

import com.techcareer.error.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {TodoNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDTO resourceNotFoundException(TodoNotFoundException ex, HttpServletRequest request) {
        ErrorDTO error = new ErrorDTO(new Date(),
                HttpStatus.NOT_FOUND.value(),
                request.getServletPath(),
                ex.getMessage());

        return error;
    }
}
