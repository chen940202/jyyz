package com.chen.wechat.mapper;


import com.chen.wechat.model.WxUserInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chen
 * @email sunlightcs@gmail.com
 * @date 2020-01-20 13:01:53
 */

public interface WxUserInfoDao {
	int insert(WxUserInfoEntity wxUserInfoEntity);
}
