package com.chen.wechat.domain.vo.response;


import com.chen.wechat.constant.CodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("返回结果集")
public class ResponseBuilder {

    @ApiModelProperty(value = "状态")
    private String code = "success";
    @ApiModelProperty(value = "错误信息")
    private String message;
    @ApiModelProperty(value = "结果集")
    private Object result;

    public static DataResponse success(String code, String msg) {
        DataResponse baseResult = new DataResponse();
        baseResult.setCode(CodeConstant.SUCCESS);
        baseResult.setMessage(msg);
        return baseResult;
    }

    public static DataResponse success(Object result) {
        DataResponse baseResult = new DataResponse();
        baseResult.setCode(CodeConstant.SUCCESS);
        baseResult.setData(result);
        return baseResult;
    }
//    private static void setPage(Object result, DataResponse dataResponse) {
//        if (result instanceof Page) {
//            Page<?> pageData = (Page<?>) result;
//            BasePageResp page = new BasePageResp();
//            page.setPageNum(pageData.getPageNum());
//            page.setPageSize(pageData.getPageSize());
//            page.setPages(pageData.getPages());
//            page.setTotal(pageData.getTotal());
//            page.setResult(result);
//            dataResponse.setData(page);
//        }
//    }

    public static DataResponse success() {
        DataResponse baseResult = new DataResponse();
        baseResult.setCode(CodeConstant.SUCCESS);
        return baseResult;
    }


//    public static DataResponse build(String code, String message, Object result) {
//        DataResponse baseResponse = new DataResponse();
//        baseResponse.setCode(code);
//        baseResponse.setResult(result);
//        baseResponse.setMessage(message);
//
//        return baseResponse;
//    }
//
//    public static BaseResponse build(String code, String message) {
//        BaseResult baseResponse = new BaseResult();
//        baseResponse.setCode(code);
//        baseResponse.setMessage(message);
//        return baseResponse;
//    }


    public static BaseResponse failed(String code, String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        return baseResponse;
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