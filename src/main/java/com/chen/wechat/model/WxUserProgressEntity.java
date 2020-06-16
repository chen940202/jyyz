package com.chen.wechat.model;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author chen
 * @email sunlightcs@gmail.com
 * @date 2020-01-20 13:01:53
 */
@Data
public class WxUserProgressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 进度id
     */
    private Long progressId;
    /**
     * 微信用户id
     */
    private Long wxUserId;
    /**
     * 微信openid
     */
    private String wxOpenId;
    /**
     * 最后执行配置id
     */
    private Long lastConfigId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
