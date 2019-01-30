package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysUser;
import com.iamlook.um.service.ISysUserService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysUserService isysUserService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysUser:view")
    public @ResponseBody
        ResultInfo<List<SysUser>> listData(SysUser sysUser, Integer page, Integer limit){
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>(sysUser);
        Page<SysUser> pageObj = isysUserService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysUser:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysUser sysUser){
        boolean b = isysUserService.insert(sysUser);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysUser:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysUserService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysUser:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysUser sysUser){
        boolean b = isysUserService.updateById(sysUser);
        return new ResultInfo<>(b);
    }

}

