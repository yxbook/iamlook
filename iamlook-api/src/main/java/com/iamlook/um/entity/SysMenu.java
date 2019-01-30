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
 * 菜单权限表
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
	@TableId(value="menu_id", type= IdType.AUTO)
	private Integer menuId;
    /**
     * 菜单名称
     */
    @TableField("menu_name")
	private String menuName;
    /**
     * 父菜单ID
     */
    @TableField("parent_id")
	private Integer parentId;
    /**
     * 显示顺序
     */
    @TableField("order_num")
	private Integer orderNum;
    /**
     * 请求地址
     */
	private String url;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField("menu_type")
	private String menuType;
    /**
     * 菜单状态（0显示 1隐藏）
     */
	private String visible;
    /**
     * 权限标识
     */
	private String perms;
    /**
     * 菜单图标
     */
	private String icon;
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
		return this.menuId;
	}

}
