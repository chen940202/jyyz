package com.chen.wechat.config;

import com.chen.wechat.domain.WechatInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Component
public class WechatConfig {

    @Bean(name = "wechatInfo")
    public WechatInfo wechatInfo(){
        WechatInfo wechatInfo = new WechatInfo();
        wechatInfo.setAesKey("cLaPwFjaIC0JFSSICkJhVlauimMKJxX2R4r1hjF94wL");
        wechatInfo.setAppId("wx39c69fdaa8a956f1");
        wechatInfo.setToken("bzcrl2019");
        return wechatInfo;
    }
}
