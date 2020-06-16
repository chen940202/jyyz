package com.chen.wechat.mapper;

import com.chen.wechat.model.WxUserProgressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author chen
 * @email sunlightcs@gmail.com
 * @date 2020-01-20 13:01:53
 */

public interface WxUserProgressDao {
	int insert(WxUserProgressEntity wxUserProgressEntity);
    WxUserProgressEntity findLastInfoByOpenId(@Param("openId") String openId);
}
