package com.chen.wechat.domain.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 6/20 16:31
 * @Description:
 */
@ApiModel("接口请求系统-参数对象")
@Data
public class BaseRequest {
    @ApiModelProperty(value = "签名参数")
//    @NotNull(message = "session_id不能为空")
    private String session_id;
//
//    @ApiModelProperty(value = "接口签名", required = true)
//    @NotBlank(message = "sign 不能为空")
//    private String sign;

}
