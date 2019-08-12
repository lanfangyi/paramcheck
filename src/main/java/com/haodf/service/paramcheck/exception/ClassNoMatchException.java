package com.haodf.service.paramcheck.exception;

public class ClassNoMatchException extends RuntimeException {

    public ClassNoMatchException() {
    }

    public ClassNoMatchException(String message) {
        super(message);
    }

    public ClassNoMatchException(Throwable cause) {
        super(cause);
    }

    public ClassNoMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
