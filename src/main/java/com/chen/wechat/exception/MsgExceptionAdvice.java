package com.chen.wechat.exception;


import com.chen.wechat.constant.CodeConstant;
import com.chen.wechat.domain.vo.response.BaseResponse;
import com.chen.wechat.domain.vo.response.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2019/4/3 10:10
 * @Description: 接口全局异常处理
 */
@ControllerAdvice(basePackages = "com.qianxingniwo")
public class MsgExceptionAdvice {

    private static Logger logger = LoggerFactory.getLogger(MsgExceptionAdvice.class);

    /**
     * 参数异常捕获
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object constraintViolationExceptionHandler(ConstraintViolationException ex) {
        BaseResponse errorWebResult = new BaseResponse();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        errorWebResult.setMessage(msgList.toString());
        errorWebResult.setCode(CodeConstant.FAILURE);
        logger.error("参数校验失败==>:" + errorWebResult);
        return errorWebResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BaseResponse errorWebResult = new BaseResponse();
        errorWebResult.setCode(CodeConstant.FAILURE);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            errorWebResult.setMessage(errorMessage);
            logger.error("参数校验失败==>:" + errorWebResult);
            return errorWebResult;
        }
        errorWebResult.setMessage(ex.getMessage());
        logger.error("参数校验失败==>:" + errorWebResult);
        return errorWebResult;
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object getBindException(BindException ex) {
        BaseResponse errorWebResult = new BaseResponse();
        errorWebResult.setCode(CodeConstant.FAILURE);
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            errorWebResult.setMessage(errorMessage);
            logger.error("参数校验失败==>:" + errorWebResult);
            return errorWebResult;
        }
        errorWebResult.setMessage(ex.getMessage());
        logger.error("参数校验失败==>:" + errorWebResult);
        return errorWebResult;
    }


    /**
     * 不支持当前媒体类型 参数类型不对
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Object handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型!" + e.getMessage());
        e.printStackTrace();
        return BaseResponse.failed("不支持当前媒体类型");
    }


    /**
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public Object illegalStateException(IllegalStateException e) {
        logger.error("参数异常!" + e);
        e.printStackTrace();
        // 默认为 系统异常
        return BaseResponse.failed("参数异常");

    }


    /**
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Object NoHandlerFoundException(Exception e) {
        logger.error("请求接口地址有误!" + e);
        e.printStackTrace();
        // 默认为 系统异常
        return BaseResponse.failed("请求接口地址有误");
    }

    /**
     * ParamException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Object BusinessException(BusinessException e,HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        String url = request.getRequestURL().toString();
        String error = stringBuilder.append("\n").append("接口地址:").append(url).append("\n").append("错误信息:").append(e.getMessage()).toString();
        logger.error("业务系统异常:"+"\n"+error);
        if(url.contains("/api/product/detail/url")){
            //特殊处理
            return ResponseBuilder.success();
        }

//        if(CodeConstant.dataNotExists.equals(e.getCode())){
//            //特殊处理
//            return ResponseBuilder.success();
//        }
        // 默认为 系统异常
        return BaseResponse.failed(e.getCode(),e.getMessage());
    }


    /**
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e, HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();

        String error = stringBuilder.append("\n").append("接口地址:").append(request.getRequestURL()).append("\n").append("错误信息:").append(e.getMessage()).append("\n").append(getErrorInfo(e)).append("\n").toString();
        logger.error("系统未分类异常:"+"\n"+error);
        e.printStackTrace();
        // 默认为 系统异常
        return BaseResponse.failed();
    }


    private String getErrorInfo(Exception e){
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ;i < stackTrace.length-1;i++){
            stringBuilder.append(stackTrace[i]).append("\n");
        }
        String string = stringBuilder.toString();
        return string;
    }
}
