package com.lanfangyi.service.paramcheck.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/25 3:24 PM
 */
@Getter
@Setter
public class BaseRequest {

    @ApiModelProperty(value = "唯一请求号", example = "1234567890")
    private String reqNo;

    @ApiModelProperty(value = "当前请求的时间戳", example = "0")
    private int timeStamp;


    public BaseRequest() {
        this.timeStamp = ((int) (System.currentTimeMillis() / 1000));
    }

    public BaseRequest(String reqNo, int timeStamp) {
        this.reqNo = reqNo;
        this.timeStamp = timeStamp;
    }

    public BaseRequest(String reqNo) {
        this.reqNo = reqNo;
        this.timeStamp = ((int) (System.currentTimeMillis() / 1000));
    }
}
