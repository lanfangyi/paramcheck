package com.lanfangyi.service.paramcheck.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lanfangyi@haodf.com
 * @version 1.0
 * @since 2019/8/25 3:31 PM
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BaseResponse<T> implements Serializable {

    @ApiModelProperty(value = "状态码", example = "0")
    private int code;

    @ApiModelProperty(value = "相关信息", example = "success")
    private String message;

    @ApiModelProperty(value = "请求的唯一标识", example = "0")
    private String reqNo;

    @ApiModelProperty(value = "请求返回的数据", example = "a")
    private T dataBody;

    public BaseResponse(T dataBody) {
        this.dataBody = dataBody;
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int code, String message, T dataBody) {
        this.code = code;
        this.message = message;
        this.dataBody = dataBody;
    }

    public BaseResponse(int code, String message, String reqNo, T dataBody) {
        this.code = code;
        this.message = message;
        this.reqNo = reqNo;
        this.dataBody = dataBody;
    }

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMessage("");
        baseResponse.setDataBody(data);
        return baseResponse;
    }
}
