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
public class WxUserInfo {
    private Integer userId;
    private String userName;
    private String nikename;
    private String openid;
    private String appid;
    private String city;
    private String province;
    private String  sex;
    private String headimgurl;
    private Byte status;

}
