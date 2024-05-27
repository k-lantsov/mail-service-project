package com.example.shared.exception;

public class NoDataException extends RuntimeException{

    public NoDataException() {
    }

    public NoDataException(String message) {
        super(message);
    }

    public NoDataException(Throwable cause) {
        super(cause);
    }

    public NoDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
