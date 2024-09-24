package com.emazon.transaccional_api.aplication.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

import com.emazon.transaccional_api.domain.exceptions.ErrorExceptionArticleNotFound;
import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ControlAdvise {
    @ExceptionHandler(ErrorFeignException.class)
    public ResponseEntity<?> handleErrorFeignException(ErrorFeignException ex, WebRequest request) {
        // Puedes personalizar el formato de la respuesta de error aquí
        ExceptionResponse errorResponse = new ExceptionResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),
        ex.getMessage(),
        request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
    
    @ExceptionHandler(ErrorExceptionArticleNotFound.class)
    public ResponseEntity<?> handleErrorFeignException(ErrorExceptionArticleNotFound ex, WebRequest request) {
        ExceptionResponse errorResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(),
        ex.getMessage(),
        request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = "";
        if (ex.getMessage() != null && ex.getMessage().contains(":")) {
            String[] parts = ex.getMessage().split(":");
            if (parts.length > 1) {
                errorMessage = parts[1].trim();
            }
        }
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validation failed");
        response.setDetails(errorMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = "";
        if (ex.getMessage() != null && ex.getMessage().contains(":")) {
            String[] parts = ex.getMessage().split(":");
            if (parts.length > 1) {
                errorMessage = parts[1].trim();
            }
        }

        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validation failed");
        response.setDetails(errorMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
