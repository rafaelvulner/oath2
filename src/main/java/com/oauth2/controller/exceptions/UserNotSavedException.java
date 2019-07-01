package com.oauth2.controller.exceptions;

public class UserNotSavedException extends RuntimeException{

    public UserNotSavedException() {
    }

    public UserNotSavedException(String s) {
        super(s);
    }
}
