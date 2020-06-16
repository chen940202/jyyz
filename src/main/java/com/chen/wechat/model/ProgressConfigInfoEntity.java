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
public class ProgressConfigInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 进度配置id
     */
    private Long configId;
    /**
     * 配置名称
     */
    private String configName;
    /**
     * 配置描述
     */
    private String configContent;

}
