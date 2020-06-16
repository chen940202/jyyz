package com.chen.wechat.config;

import com.chen.wechat.service.WxService;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Component
public class WxAopConfig {

    @Autowired
    private WxService wxService;
    @Autowired
    private HttpServletResponse httpServletResponse;

////    @Pointcut("execution(* com.chen.wechat.controller.*())")
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//    public void wxCallback(){
//
//    }
//
//    @AfterReturning(returning = "o",pointcut = "wxCallback()")
//    public void beforeCallback(Object o, JoinPoint joinPoint){
//        PrintWriter printWriter = null;
//        try {
//            printWriter = httpServletResponse.getWriter();
//            if(o instanceof String){
//                printWriter.write(((String) o).toCharArray());
//            }
//            printWriter.flush();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        } finally {
//            IOUtils.closeQuietly(printWriter);
//        }
//    }
}
