package com.chen.wechat.service;

import com.chen.wechat.domain.WxApiResult;
import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.domain.WxUserInfo;
import com.chen.wechat.domain.vo.response.BaseResponse;
import org.apache.xmlbeans.impl.tool.BaseSchemaResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
public interface WxService {


    /**
     * 获取微信回调消息
     */

    public WxApiResult getWxInfo(HttpServletRequest httpServletRequest);

    /**
     * 处理微信信息
     */
    public Object WxHandle();

    /**
     * 获取关注微信用户信息
     */
    String follow(WxMessageVo wxMessageVo);




    /**
     * 响应微信服务器
     */
    PrintWriter WxCallback(HttpServletRequest request , HttpServletResponse response) throws IOException;

    
}
