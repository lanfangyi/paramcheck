package com.lanfangyi.service.paramcheck.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/10/31 11:41 AM
 */
@RestController
@RequestMapping("/hebernate")
public class HebernateCheckService {

    /**
     * 用于测试
     *
     * @param id id数不能小于10 @RequestParam类型的参数需要在Controller上增加@Validated
     * @return
     */
    @GetMapping(value = "/info")
    public String test(@Min(value = 10, message = "id最小只能是10") @RequestParam("id")
                           Integer id) {
        return "恭喜你拿到参数了";
    }
}
