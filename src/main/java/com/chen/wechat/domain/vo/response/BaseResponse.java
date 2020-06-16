package com.chen.wechat.domain.vo.response;


import com.chen.wechat.constant.CodeConstant;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    @ApiModelProperty(value = "状态")
    private String code = "success";
    @ApiModelProperty(value = "错误信息")
    private String message;

    public BaseResponse() {
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResponse failed() {
        BaseResponse baseResult = new BaseResponse();
        baseResult.setCode(CodeConstant.FAILURE);
        baseResult.setMessage("系统异常");
        return baseResult;
    }

    public static BaseResponse failed(String message) {
        BaseResponse baseResult = new BaseResponse();
        baseResult.setCode(CodeConstant.FAILURE);
        baseResult.setMessage(message);
        return baseResult;
    }

    public static BaseResponse failed(String code, String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        return baseResponse;
    }

//    @Override
//    public String toString() {
//        return "BaseResponse{" +
//                "code='" + code + '\'' +
//                ", message='" + message + '\'' +
//                '}';
//    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
