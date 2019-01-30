package com.iamlook.um.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
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
 * 用户信息表
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	@TableId(value="user_id", type= IdType.AUTO)
	private Integer userId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
	private Integer deptId;
    /**
     * 登录账号
     */
    @TableField("login_name")
	private String loginName;
    /**
     * 用户昵称
     */
    @TableField("user_name")
	private String userName;
    /**
     * 用户类型（00系统用户）
     */
    @TableField("user_type")
	private String userType;
    /**
     * 用户邮箱
     */
	private String email;
    /**
     * 手机号码
     */
	private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
	private String sex;
    /**
     * 头像路径
     */
	private String avatar;
    /**
     * 密码
     */
	private String password;
    /**
     * 盐加密
     */
	private String salt;
    /**
     * 帐号状态（0正常 1停用）
     */
	private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
	private String delFlag;
    /**
     * 最后登陆IP
     */
    @TableField("login_ip")
	private String loginIp;
    /**
     * 最后登陆时间
     */
    @TableField("login_date")
	private Date loginDate;
    /**
     * 创建者
     */
    @TableField("create_by")
	private String createBy;
    /**
     * 创建时间
     */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
	private String updateBy;
    /**
     * 更新时间
     */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updateTime;
    /**
     * 备注
     */
	private String remark;


	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

}
