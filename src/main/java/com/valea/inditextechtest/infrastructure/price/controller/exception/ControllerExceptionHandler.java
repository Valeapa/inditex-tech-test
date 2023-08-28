package com.valea.inditextechtest.infrastructure.price.controller.exception;

import com.valea.inditextechtest.infrastructure.price.controller.dto.ExceptionDto;
import com.valea.inditextechtest.domain.price.exception.PriceNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


/**
 * Centralized controller exception handler.
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ExceptionDto> handlePriceNotFoundException(PriceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDto(ex.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
