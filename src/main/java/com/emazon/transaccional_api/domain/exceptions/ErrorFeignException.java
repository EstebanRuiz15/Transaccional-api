package com.emazon.transaccional_api.domain.exceptions;

public class ErrorFeignException extends RuntimeException {
    public ErrorFeignException(String message){
        super (message);
    }
}
