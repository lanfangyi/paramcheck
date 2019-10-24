package com.lanfangyi.service.paramcheck.exception;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class NoRationalNumberException extends RuntimeException {

    public NoRationalNumberException() {
        super("参数不是整数，校验不通过");
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
