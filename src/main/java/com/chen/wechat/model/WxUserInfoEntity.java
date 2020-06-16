package com.chen.wechat.model;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chen
 * @email sunlightcs@gmail.com
 * @date 2020-01-20 13:01:53
 */
@Data
public class WxUserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 微信用户id
	 */
	private Long wxUserId;
	/**
	 * 微信昵称
	 */
	private String wxUserName;
	/**
	 * openid
	 */
	private String wxOpenId;

	/**
	 * 状态
	 */
	private Byte status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
