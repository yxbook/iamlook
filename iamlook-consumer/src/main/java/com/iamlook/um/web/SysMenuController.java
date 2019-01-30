package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysMenu;
import com.iamlook.um.service.ISysMenuService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysMenuService isysMenuService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysMenu:view")
    public @ResponseBody
        ResultInfo<List<SysMenu>> listData(SysMenu sysMenu, Integer page, Integer limit){
        EntityWrapper<SysMenu> wrapper = new EntityWrapper<>(sysMenu);
        Page<SysMenu> pageObj = isysMenuService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysMenu:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysMenu sysMenu){
        boolean b = isysMenuService.insert(sysMenu);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysMenu:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysMenuService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysMenu:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysMenu sysMenu){
        boolean b = isysMenuService.updateById(sysMenu);
        return new ResultInfo<>(b);
    }

}

