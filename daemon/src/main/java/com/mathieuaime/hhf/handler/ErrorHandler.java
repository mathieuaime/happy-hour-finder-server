package com.mathieuaime.hhf.handler;

import com.mathieuaime.hhf.exception.BadRequestException;
import com.mathieuaime.hhf.exception.ResourceNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException e) {
        var body = Map.of(
                "timestamp", LocalDateTime.now(),
                "code", e.getCode(),
                "params", e.getParams()
        );

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        var body = Map.of(
                "timestamp", LocalDateTime.now(),
                "code", e.getCode(),
                "params", e.getParams()
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        var body = Map.of(
                "timestamp", LocalDateTime.now(),
                "message", "An unknown error occurred"
        );

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(groupingBy(
                        FieldError::getField,
                        TreeMap::new,
                        mapping(DefaultMessageSourceResolvable::getDefaultMessage, toSet())
                ));

        var body = Map.of(
                "timestamp", LocalDate.now(),
                "status", status.value(),
                "errors", errors
        );

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
