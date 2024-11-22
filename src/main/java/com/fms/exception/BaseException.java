package com.fms.exception;

public class BaseException extends RuntimeException{
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
