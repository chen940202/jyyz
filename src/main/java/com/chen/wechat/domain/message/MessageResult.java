package com.chen.wechat.domain.message;

import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.mapper.WxUserProgressDao;
import com.chen.wechat.model.WxUserProgressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Component
public abstract class MessageResult {
    @Autowired
    private WxUserProgressDao wxUserProgressDao;

    public WxMessageVo result(Object o, String fromUserName, String toUserName, String event) {

        WxMessageVo wxMessageVo = new WxMessageVo();
        wxMessageVo.setMsgType(getType());
        wxMessageVo.setContent(o.toString());
        wxMessageVo.setCreateTime(new Date().getTime());
        wxMessageVo.setFromUserName(fromUserName);
        wxMessageVo.setToUserName(toUserName);
        wxMessageVo.setEvent(event);
        //查询当前所在进度
        WxUserProgressEntity lastInfoByOpenId = wxUserProgressDao.findLastInfoByOpenId(wxMessageVo.getToUserName());
        if(lastInfoByOpenId == null){
            wxMessageVo.setContent("您如果没有关注公众号，请先关注公众号，如果您已经关注过，请重新关注一次，谢谢");
            return wxMessageVo;
        }
        wxMessageVo.setProgress(lastInfoByOpenId.getLastConfigId());
        wxMessageVo.setContent(getContent(wxMessageVo));
        Long nextProgress = wxMessageVo.getNextProgress();
        if(nextProgress == null){
            return wxMessageVo;
        }
        //更新当前进度的状态
        WxUserProgressEntity wxUserProgressEntity = new WxUserProgressEntity();
        wxUserProgressEntity.setWxOpenId(toUserName);
        wxUserProgressEntity.setLastConfigId(nextProgress);
        int insert = wxUserProgressDao.insert(wxUserProgressEntity);
        if(insert == 0){
            wxMessageVo.setContent("请您先关注公众号");
        }
        return wxMessageVo;
    }

    public abstract String getContent(WxMessageVo wxMessageVo);

    public abstract String getType();

    public abstract String toXml(WxMessageVo wxMessageVo);

    public enum messageType {
        TEXT("text", "文本类型", "textResult"),
        EVENT("event", "关注", "eventResult");
        private String type;
        private String desc;
        private String className;

        messageType(String type, String desc, String className) {
            this.type = type;
            this.desc = desc;
            this.className = className;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}

