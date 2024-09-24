package com.emazon.transaccional_api.domain.exceptions;

public class ErrorExceptionArticleNotFound extends RuntimeException {
    public ErrorExceptionArticleNotFound (String message) {
            super(message);
        }
}
