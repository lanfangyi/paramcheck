package com.lanfangyi.service.paramcheck.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResult implements Serializable {

    private int code;

    private String msg;

    private Throwable throwable;

}
