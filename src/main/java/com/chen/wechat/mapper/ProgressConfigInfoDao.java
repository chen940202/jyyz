package com.chen.wechat.mapper;

import com.chen.wechat.model.ProgressConfigInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author chen
 * @email sunlightcs@gmail.com
 * @date 2020-01-20 13:01:53
 */
public interface ProgressConfigInfoDao {
	ProgressConfigInfoEntity findInfoById(@Param("configId") Long configId);
}
