package com.lanfangyi.service.paramcheck.service;

import com.lanfangyi.service.paramcheck.annotation.Valid;
import com.lanfangyi.service.paramcheck.annotation.activeannotation.Between;
import com.lanfangyi.service.paramcheck.resp.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/9/24 2:40 PM
 */
@RestController
@RequestMapping("/between")
public class BetweenService {

    @GetMapping("/test")
    @Valid
    public BaseResponse<Integer> test(@Between(max = 20,min = 10) int a) {
        return BaseResponse.success(a);
    }

    @GetMapping("/test2")
    @Valid
    public BaseResponse<Integer> test2(@Between(max = 20,min = 10) Integer a) {
        return BaseResponse.success(a);
    }

}
