package com.lanfangyi.service.paramcheck.utils;


import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/20 10:44 PM
 */
public class ServiceMethodTestUtil {

    public static Map<String, Object> getTestCase( Class clazz,  String methodName, int caseNum) {
        if (null == clazz || StringUtils.isEmpty(methodName) || caseNum < 1) {
            return new HashMap<>();
        }
        System.out.println("----00q3w234");

        System.out.println(Integer.class.isAssignableFrom(clazz));
        return null;
    }
}
