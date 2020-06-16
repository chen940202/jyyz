package com.chen.wechat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 2020/1/19
 * @Description:
 */
@Data
public class WxMessageVo {
    @JsonProperty(value = "ToUserName")
    private String ToUserName;

    @JsonProperty(value = "FromUserName")
    private String FromUserName;
    @JsonProperty(value = "CreateTime")
    private Long CreateTime;
    @JsonProperty(value = "MsgType")
    private String MsgType;
    @JsonProperty(value = "Content")
    private String Content;
    @JsonProperty(value = "MsgId")
    private String MsgId;
    private String Event;
    /**
     * 进度id
     */
    private Long progress;
    /**
     * 下一个进度
     */
    private Long nextProgress;
}
