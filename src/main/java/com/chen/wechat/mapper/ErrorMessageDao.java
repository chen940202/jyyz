package com.chen.wechat.mapper;

import com.chen.wechat.model.ErrorMessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author chen
 * @email sunlightcs@gmail.com
 * @date 2020-01-24 12:03:58
 */
public interface ErrorMessageDao {
  ErrorMessageEntity findInfoByErrorCode(@Param("errorCode") String errorCode);
}
