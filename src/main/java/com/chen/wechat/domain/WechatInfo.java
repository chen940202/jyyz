package com.chen.wechat.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Getter
@Setter
public class WechatInfo {
    private String appId;
    private String token;
    private String AesKey;
}
