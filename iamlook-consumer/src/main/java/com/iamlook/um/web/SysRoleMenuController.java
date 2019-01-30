package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysRoleMenu;
import com.iamlook.um.service.ISysRoleMenuService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 角色和菜单关联表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysRoleMenuService isysRoleMenuService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysRoleMenu:view")
    public @ResponseBody
        ResultInfo<List<SysRoleMenu>> listData(SysRoleMenu sysRoleMenu, Integer page, Integer limit){
        EntityWrapper<SysRoleMenu> wrapper = new EntityWrapper<>(sysRoleMenu);
        Page<SysRoleMenu> pageObj = isysRoleMenuService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysRoleMenu:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysRoleMenu sysRoleMenu){
        boolean b = isysRoleMenuService.insert(sysRoleMenu);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysRoleMenu:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysRoleMenuService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysRoleMenu:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysRoleMenu sysRoleMenu){
        boolean b = isysRoleMenuService.updateById(sysRoleMenu);
        return new ResultInfo<>(b);
    }

}

