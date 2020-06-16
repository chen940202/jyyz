package com.chen.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.chen.wechat.constant.CodeConstant;
import com.chen.wechat.domain.WechatInfo;
import com.chen.wechat.domain.WxApiResult;
import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.domain.message.MessageResult;
import com.chen.wechat.domain.message.WxMessageResultFactory;
import com.chen.wechat.exception.BusinessException;
import com.chen.wechat.mapper.WxUserInfoDao;
import com.chen.wechat.mapper.WxUserProgressDao;
import com.chen.wechat.model.WxUserInfoEntity;
import com.chen.wechat.model.WxUserProgressEntity;
import com.chen.wechat.service.WxService;
import com.chen.wechat.utils.WxParseXml;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Service
public class WxServiceImpl implements WxService {
    Logger logger = LoggerFactory.getLogger(WxServiceImpl.class);

    @Autowired
    private WechatInfo wechatInfo;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpServletResponse httpServletResponse;
    @Autowired
    private WxMessageResultFactory wxMessageResultFactory;
    @Autowired
    private WxUserProgressDao wxUserProgressDao;
    @Autowired
    private WxUserInfoDao wxUserInfoDao;

    @Override
    public WxApiResult getWxInfo(HttpServletRequest httpServletRequest) {
        logger.info("1.收到微信服务器消息");
        Map<String, String> map = null;
        try {
            map = WxParseXml.parseXml(httpServletRequest);
            logger.info("2.获取微信数据:[{}]", JSON.toJSONString(map));
        } catch (Exception e) {
            return WxApiResult.failure(CodeConstant.INTERFACE_ERROR, "请求微信接口错误");
        }
        return WxApiResult.success(map);
    }

    @Override
    public Object WxHandle() {
        WxApiResult wxInfo = getWxInfo(httpServletRequest);
        Object result = wxInfo.getResult();
        if (result == null) {
            throw new BusinessException(CodeConstant.FAILURE, "微信处理结果为空");
        }
        WxMessageVo wxMessageVo = JSON.parseObject(JSON.toJSONString(result), WxMessageVo.class);
        Object o = null;
        switch (wxMessageVo.getMsgType()) {
            case "event":
                //关注和取消关注
                o = follow(wxMessageVo);
                break;

            case "text":
                o = test(wxMessageVo);
//                writer.flush();
                break;
        }

        return o;
    }

    @Override
    public String follow(WxMessageVo wxMessageVo) {
        wxMessageVo.setContent("follow");
        //存入用户数据
        WxUserInfoEntity wxUserInfoEntity = new WxUserInfoEntity();
        wxUserInfoEntity.setWxOpenId(wxMessageVo.getFromUserName());
        int insert = wxUserInfoDao.insert(wxUserInfoEntity);
        String event = wxMessageVo.getEvent();
        //存入记录
        if(event.equals("subscribe")) {
            WxUserProgressEntity wxUserProgressEntity = new WxUserProgressEntity();
            wxUserProgressEntity.setWxOpenId(wxMessageVo.getFromUserName());
            wxUserProgressEntity.setLastConfigId(1l);
            int record = wxUserProgressDao.insert(wxUserProgressEntity);
        }
        String result = wxMessageResultFactory.create(MessageResult.messageType.EVENT.getType(), wxMessageVo);
        return result;
    }

    public String test(WxMessageVo wxMessageVo) {
        String result = wxMessageResultFactory.create(MessageResult.messageType.TEXT.getType(), wxMessageVo);
        return result;
    }


    @Override
    public PrintWriter WxCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean flag = checkSign(request);
        PrintWriter writer = null;
        writer = response.getWriter();
        if (flag) {
            writer.print(request.getParameter("echostr"));
            writer.flush();
            logger.info("响应成功");
        }
        return writer;
    }


    /**
     * 微信签名认证
     *
     * @param
     * @param
     */

    private boolean checkSign(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        List<String> list = new ArrayList<>();
        list.add(wechatInfo.getToken());
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        StringBuilder stringBuilder = new StringBuilder();
        String pass = stringBuilder.append(list.get(0)).append(list.get(1)).append(list.get(2)).toString();
        String sha1Hex = DigestUtils.sha1Hex(pass);
        if (sha1Hex.equals(signature)) {
            logger.info("检查参数成功");
            return true;
        }
        return false;
    }
}
