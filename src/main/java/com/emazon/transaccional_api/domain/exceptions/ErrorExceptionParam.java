package com.emazon.transaccional_api.domain.exceptions;


public class ErrorExceptionParam extends RuntimeException {
    public ErrorExceptionParam (String message) {
            super(message);
        }

}