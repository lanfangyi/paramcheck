package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Min;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.NotEmpty;
import com.lanfangyi.service.paramcheck.aop.validate.ErrorLevelEnum;
import com.lanfangyi.service.paramcheck.resp.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/24 2:11 PM
 */
@RestController
@RequestMapping("/notEmpty")
public class NotEmptyService {

    @PostMapping("/test")
    @Valid(addErrLog = true, errLogLevel = ErrorLevelEnum.ERROR)
    public BaseResponse<Integer> test(@NotEmpty(maxSize = 3, minSize = 1) @RequestBody List<Long> a) {
        System.out.println(a);
        return BaseResponse.success(1);
    }
}
