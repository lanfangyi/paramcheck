package com.haodf.service.paramcheck.exception;

public class TypeMismatchException extends RuntimeException {

    public TypeMismatchException() {
    }

    public TypeMismatchException(String message) {
        super(message);
    }

    public TypeMismatchException(Throwable cause) {
        super(cause);
    }

    public TypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
