package com.lanfangyi.service.paramcheck.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResult implements Serializable {

    private int code;

    private String msg;

    private Throwable throwable;

}
