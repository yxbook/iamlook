package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysRole;
import com.iamlook.um.service.ISysRoleService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysRoleService isysRoleService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysRole:view")
    public @ResponseBody
        ResultInfo<List<SysRole>> listData(SysRole sysRole, Integer page, Integer limit){
        EntityWrapper<SysRole> wrapper = new EntityWrapper<>(sysRole);
        Page<SysRole> pageObj = isysRoleService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysRole:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysRole sysRole){
        boolean b = isysRoleService.insert(sysRole);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysRole:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysRoleService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysRole:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysRole sysRole){
        boolean b = isysRoleService.updateById(sysRole);
        return new ResultInfo<>(b);
    }

}

