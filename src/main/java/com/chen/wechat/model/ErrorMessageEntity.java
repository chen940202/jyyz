package com.chen.wechat.model;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author chen
 * @email sunlightcs@gmail.com
 * @date 2020-01-24 12:03:58
 */
@Data
public class ErrorMessageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 错误信息id
     */
    private Long errorId;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;
    /**
     * 0-正常，1-禁用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
