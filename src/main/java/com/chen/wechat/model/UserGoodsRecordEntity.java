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
public class UserGoodsRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 记录id
	 */
	private Long recordId;
	/**
	 * 用户id
	 */
	private Long wxUserId;
	/**
	 * openId
	 */
	private String wxOpenId;
	/**
	 * 文件类型
	 */
	private Integer type;
	/**
	 * 记录描述
	 */
	private String recordDesc;
	/**
	 * 账户
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passWord;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
