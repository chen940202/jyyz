package com.chen.wechat.mapper;

import com.chen.wechat.model.UserGoodsRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chen
 * @email sunlightcs@gmail.com
 * @date 2020-01-20 13:01:53
 */

public interface UserGoodsRecordDao {
    List<UserGoodsRecordEntity> findInfoByUserId(@Param("openId") String openId);
    int insert(UserGoodsRecordEntity userGoodsRecordEntity);
    int delete(@Param("recordId") Long recordId,@Param("status") Integer status);
}
