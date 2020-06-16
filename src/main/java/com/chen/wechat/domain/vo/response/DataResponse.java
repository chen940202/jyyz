package com.chen.wechat.domain.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: wangg
 * @Date: 2018/6/5 10:48
 * @Description:
 */

@Data
public class DataResponse<T> extends BaseResponse{

    @ApiModelProperty(value = "数据内容")
    private T data;


}
