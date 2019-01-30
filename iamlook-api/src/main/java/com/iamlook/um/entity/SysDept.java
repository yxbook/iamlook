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
 * 部门表
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
	@TableId(value="dept_id", type= IdType.AUTO)
	private Integer deptId;
    /**
     * 父部门id
     */
    @TableField("parent_id")
	private Integer parentId;
    /**
     * 祖级列表
     */
	private String ancestors;
    /**
     * 部门名称
     */
    @TableField("dept_name")
	private String deptName;
    /**
     * 显示顺序
     */
    @TableField("order_num")
	private Integer orderNum;
    /**
     * 负责人
     */
	private String leader;
    /**
     * 联系电话
     */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 部门状态（0正常 1停用）
     */
	private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
	private String delFlag;
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


	@Override
	protected Serializable pkVal() {
		return this.deptId;
	}

}
