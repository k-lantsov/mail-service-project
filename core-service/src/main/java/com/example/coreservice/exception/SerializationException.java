package com.example.coreservice.exception;

public class SerializationException extends RuntimeException{

    public SerializationException() {
    }

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(Throwable cause) {
        super(cause);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
