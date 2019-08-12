package com.haodf.service.paramcheck.utils;

import com.haodf.service.paramcheck.annotation.Valid;
import com.haodf.service.paramcheck.annotation.activeannotation.Min;
import com.haodf.service.paramcheck.annotation.activeannotation.NotBlank;
import com.haodf.service.paramcheck.annotation.activeannotation.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ServiceMethodTestUtil {

    @Valid
    public static Map<String, Object> getTestCase(@NotNull Class clazz, @NotBlank String methodName, @Min(1) int caseNum) {
        System.out.println("----00q3w234");
        return null;
    }


}
