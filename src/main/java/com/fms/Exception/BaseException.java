package com.fms.Exception;

public class BaseException extends RuntimeException{
    public BaseException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
