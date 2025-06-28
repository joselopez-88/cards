package com.eazybites.cards.exception.advice;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.eazybites.cards.exception.dto.ApiError;
import com.eazybites.cards.exception.error.CardAlreadyExistsException;
import com.eazybites.cards.exception.error.ResourceNotFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiError handlerGlobalException(Exception exception, WebRequest webRequest) {
        return ApiError.createWithMessage(webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CardAlreadyExistsException.class)
    public ApiError handlerCustomerMobileNumberExists(CardAlreadyExistsException exception, WebRequest webRequest) {
        return ApiError.createWithMessage(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiError handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        return ApiError.createWithMessage(webRequest.getDescription(false), HttpStatus.NOT_FOUND, exception.getMessage());
    }

     
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handlerMalFormedRequest(MethodArgumentNotValidException exception, WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
     
        return ApiError.createWithDetails(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, errors);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiError handleTypeMismatch(MethodArgumentTypeMismatchException exception, WebRequest webRequest) {
        return ApiError.createWithMessage(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, exception.getMessage()); 
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiError handlerPathAndRequestParam(ConstraintViolationException exception, WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(violation -> {
            String path = violation.getPropertyPath().toString();
            // Extrae el nombre del parámetro (última parte del path)
            String fieldName = path.substring(path.lastIndexOf('.') + 1);
            errors.put(fieldName, violation.getMessage());
        });
            return ApiError.createWithDetails(webRequest.getDescription(false), HttpStatus.BAD_REQUEST, errors);  
    }
}
