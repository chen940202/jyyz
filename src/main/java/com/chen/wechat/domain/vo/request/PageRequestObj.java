package com.chen.wechat.domain.vo.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Copyright (C) 四川千行你我科技有限公司
 * @Author: Chen
 * @Date: 6/25 10:49
 * @Description:
 */
public class PageRequestObj extends BaseRequest {
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    @ApiModelProperty(value = "当前页数")
    private Integer pageNo;

    public Integer getPageSize() {
        if (pageSize==null){
            return 100;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        if (pageNo==null){
            return 1;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
