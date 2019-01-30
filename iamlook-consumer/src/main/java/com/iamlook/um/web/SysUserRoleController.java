package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysUserRole;
import com.iamlook.um.service.ISysUserRoleService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户和角色关联表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysUserRoleService isysUserRoleService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysUserRole:view")
    public @ResponseBody
        ResultInfo<List<SysUserRole>> listData(SysUserRole sysUserRole, Integer page, Integer limit){
        EntityWrapper<SysUserRole> wrapper = new EntityWrapper<>(sysUserRole);
        Page<SysUserRole> pageObj = isysUserRoleService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysUserRole:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysUserRole sysUserRole){
        boolean b = isysUserRoleService.insert(sysUserRole);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysUserRole:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysUserRoleService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysUserRole:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysUserRole sysUserRole){
        boolean b = isysUserRoleService.updateById(sysUserRole);
        return new ResultInfo<>(b);
    }

}

