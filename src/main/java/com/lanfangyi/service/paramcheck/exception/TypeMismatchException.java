package com.lanfangyi.service.paramcheck.exception;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
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
