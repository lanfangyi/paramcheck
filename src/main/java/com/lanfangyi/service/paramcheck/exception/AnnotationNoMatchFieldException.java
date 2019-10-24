package com.lanfangyi.service.paramcheck.exception;


/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class AnnotationNoMatchFieldException extends RuntimeException {

    public AnnotationNoMatchFieldException() {
        super("注解不适用于此字段类型");
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
