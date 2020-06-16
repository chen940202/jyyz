package com.chen.wechat.domain.message;

import com.alibaba.fastjson.JSON;
import com.chen.wechat.domain.WxMessageVo;
import com.chen.wechat.mapper.WxUserProgressDao;
import com.chen.wechat.model.WxUserProgressEntity;
import com.chen.wechat.utils.WxParseXml;
import javafx.scene.effect.SepiaTone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Component
public class WxMessageResultFactory implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(WxMessageResultFactory.class);

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WxUserProgressDao wxUserProgressDao;




    public String create(String type, WxMessageVo wxMessageVo){
        logger.info("获取消息数据[{}]",JSON.toJSONString(wxMessageVo));
        MessageResult messageResult = map.get(type);
        String content = wxMessageVo.getContent();
        if(StringUtils.isEmpty(content)){
            return "";
        }
        excute(wxMessageVo, content);
        WxMessageVo messageVo = messageResult.result(content, wxMessageVo.getToUserName(), wxMessageVo.getFromUserName(),wxMessageVo.getEvent());
        String result = messageResult.toXml(messageVo);
        logger.info("返回消息数据[{}]", JSON.toJSONString(messageVo));
        return result;
    }

    private void excute(WxMessageVo wxMessageVo, String content) {
        WxUserProgressEntity wxUserProgressEntity = new WxUserProgressEntity();
        if(content.contains("存")){
            //插入数据
            wxUserProgressEntity.setLastConfigId(2l);
            //插入数据
            wxUserProgressEntity.setWxOpenId(wxMessageVo.getFromUserName());
            int insert = wxUserProgressDao.insert(wxUserProgressEntity);
        }else if(content.contains("取") || content.contains("读")){
            //插入数据
            wxUserProgressEntity.setLastConfigId(6l);
            //插入数据
            wxUserProgressEntity.setWxOpenId(wxMessageVo.getFromUserName());
            int insert = wxUserProgressDao.insert(wxUserProgressEntity);
        }else if(content.contains("删除")){
            wxUserProgressEntity.setLastConfigId(8l);
            //插入数据
            wxUserProgressEntity.setWxOpenId(wxMessageVo.getFromUserName());
            int insert = wxUserProgressDao.insert(wxUserProgressEntity);
        }

    }


    private static Map<String,MessageResult> map = new HashMap<>();

    /**
     * 初始化工厂
     */
    public void init() throws IllegalAccessException, InstantiationException {
        MessageResult.messageType[] values = MessageResult.messageType.values();
        for(MessageResult.messageType messageType : values){
            map.put(messageType.getType(),applicationContext.getBean(messageType.getClassName(),MessageResult.class));
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }
}
