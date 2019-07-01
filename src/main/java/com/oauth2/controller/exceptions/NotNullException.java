package com.oauth2.controller.exceptions;

public class NotNullException extends RuntimeException {

    public NotNullException() {
    }

    public NotNullException(String s) {
        super(s);
    }
}
