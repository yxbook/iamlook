package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysUserOnline;
import com.iamlook.um.service.ISysUserOnlineService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 在线用户记录 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysUserOnline")
public class SysUserOnlineController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysUserOnlineService isysUserOnlineService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysUserOnline:view")
    public @ResponseBody
        ResultInfo<List<SysUserOnline>> listData(SysUserOnline sysUserOnline, Integer page, Integer limit){
        EntityWrapper<SysUserOnline> wrapper = new EntityWrapper<>(sysUserOnline);
        Page<SysUserOnline> pageObj = isysUserOnlineService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysUserOnline:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysUserOnline sysUserOnline){
        boolean b = isysUserOnlineService.insert(sysUserOnline);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysUserOnline:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysUserOnlineService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysUserOnline:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysUserOnline sysUserOnline){
        boolean b = isysUserOnlineService.updateById(sysUserOnline);
        return new ResultInfo<>(b);
    }

}

