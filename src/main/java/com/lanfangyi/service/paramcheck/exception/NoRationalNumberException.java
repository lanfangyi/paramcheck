package com.lanfangyi.service.paramcheck.exception;

public class NoRationalNumberException extends RuntimeException {

    public NoRationalNumberException() {
    }

    public NoRationalNumberException(String message) {
        super(message);
    }

    public NoRationalNumberException(Throwable cause) {
        super(cause);
    }

    public NoRationalNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
