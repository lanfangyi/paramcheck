package com.lanfangyi.service.paramcheck.exception;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class ClassNoMatchException extends RuntimeException {

    public ClassNoMatchException() {
        super("class 类型不匹配");
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
