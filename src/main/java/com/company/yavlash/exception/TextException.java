package com.company.yavlash.exception;

public class TextException extends Exception{
    public TextException() {
    }

    public TextException(String message) {
        super(message);
    }

    public TextException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextException(Throwable cause) {
        super(cause);
    }
}