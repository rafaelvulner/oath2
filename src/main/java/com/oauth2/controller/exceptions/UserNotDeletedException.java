package com.oauth2.controller.exceptions;

public class UserNotDeletedException extends RuntimeException{

    public UserNotDeletedException() {
    }

    public UserNotDeletedException(String s) {
        super(s);
    }
}
