package com.emazon.transaccional_api.aplication.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;

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
    
}
