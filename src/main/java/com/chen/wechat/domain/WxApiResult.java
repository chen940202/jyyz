package com.chen.wechat.domain;

import com.chen.wechat.constant.CodeConstant;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
public class WxApiResult {
    private String code;
    private String message;
    private Object result;


    public static WxApiResult failure(String code,String message){
        WxApiResult wxApiResult = new WxApiResult(code,message,null);
        return wxApiResult;
    }


    public static WxApiResult success(){

        return  success("",null);
    }

    public static WxApiResult success(Object result){
        return  success("",result);
    }

    public static WxApiResult success(String message,Object result){
        WxApiResult wxApiResult = new WxApiResult(CodeConstant.SUCCESS,message,result);
        return wxApiResult;
    }

    public WxApiResult(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
