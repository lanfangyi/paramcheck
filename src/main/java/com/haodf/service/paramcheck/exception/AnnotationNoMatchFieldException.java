package com.haodf.service.paramcheck.exception;


public class AnnotationNoMatchFieldException extends RuntimeException {

    public AnnotationNoMatchFieldException() {
    }

    public AnnotationNoMatchFieldException(String message) {
        super(message);
    }

    public AnnotationNoMatchFieldException(Throwable cause) {
        super(cause);
    }

    public AnnotationNoMatchFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
