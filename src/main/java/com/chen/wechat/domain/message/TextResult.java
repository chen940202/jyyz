package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.utils.WxParseXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Service
public class TextResult extends MessageResult{

    @Autowired
    private ContentFactory contentFactory;

    @Override
    public String getContent(WxMessageVo wxMessageVo) {
        return contentFactory.create(wxMessageVo);
    }

    @Override
    public String getType() {
        return messageType.TEXT.getType();
    }


    /**
     * 返回xml格式字符串
     */
    @Override
    public String toXml(WxMessageVo wxMessageVo){
        return WxParseXml.toXml(wxMessageVo);
    }
}
