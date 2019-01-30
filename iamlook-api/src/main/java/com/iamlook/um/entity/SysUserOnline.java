package com.iamlook.um.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 在线用户记录
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user_online")
public class SysUserOnline extends Model<SysUserOnline> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户会话id
     */
	private String sessionId;
    /**
     * 登录账号
     */
    @TableField("login_name")
	private String loginName;
    /**
     * 部门名称
     */
    @TableField("dept_name")
	private String deptName;
    /**
     * 登录IP地址
     */
	private String ipaddr;
    /**
     * 登录地点
     */
    @TableField("login_location")
	private String loginLocation;
    /**
     * 浏览器类型
     */
	private String browser;
    /**
     * 操作系统
     */
	private String os;
    /**
     * 在线状态on_line在线off_line离线
     */
	private String status;
    /**
     * session创建时间
     */
    @TableField("start_timestamp")
	private Date startTimestamp;
    /**
     * session最后访问时间
     */
    @TableField("last_access_time")
	private Date lastAccessTime;
    /**
     * 超时时间，单位为分钟
     */
    @TableField("expire_time")
	private Integer expireTime;


	@Override
	protected Serializable pkVal() {
		return this.sessionId;
	}

}
