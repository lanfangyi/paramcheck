package com.lanfangyi.service.paramcheck.exception;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class TypeMismatchException extends RuntimeException {

    public TypeMismatchException() {
        super("定义的返回值类型与方法签名的返回值类型不同");
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
