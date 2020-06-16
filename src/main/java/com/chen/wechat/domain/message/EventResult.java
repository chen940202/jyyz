package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.utils.WxParseXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Service
public class EventResult extends MessageResult {

    @Autowired
    private ContentFactory contentFactory;
    @Override
    public String getContent(WxMessageVo wxMessageVo) {
        String event = wxMessageVo.getEvent();
        wxMessageVo.setEvent(null);
        if(event.equals("subscribe")){
            String content = contentFactory.create(wxMessageVo);
            return content;
        }else {
            return null;
        }
    }

    @Override
    public String getType() {
        return messageType.TEXT.getType();
    }

    @Override
    public String toXml(WxMessageVo wxMessageVo) {
        return WxParseXml.toXml(wxMessageVo);
    }
}
