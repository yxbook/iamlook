package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysUserPost;
import com.iamlook.um.service.ISysUserPostService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户与岗位关联表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysUserPost")
public class SysUserPostController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysUserPostService isysUserPostService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysUserPost:view")
    public @ResponseBody
        ResultInfo<List<SysUserPost>> listData(SysUserPost sysUserPost, Integer page, Integer limit){
        EntityWrapper<SysUserPost> wrapper = new EntityWrapper<>(sysUserPost);
        Page<SysUserPost> pageObj = isysUserPostService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysUserPost:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysUserPost sysUserPost){
        boolean b = isysUserPostService.insert(sysUserPost);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysUserPost:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysUserPostService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysUserPost:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysUserPost sysUserPost){
        boolean b = isysUserPostService.updateById(sysUserPost);
        return new ResultInfo<>(b);
    }

}

