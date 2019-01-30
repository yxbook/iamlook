package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysPost;
import com.iamlook.um.service.ISysPostService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 岗位信息表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysPost")
public class SysPostController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysPostService isysPostService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysPost:view")
    public @ResponseBody
        ResultInfo<List<SysPost>> listData(SysPost sysPost, Integer page, Integer limit){
        EntityWrapper<SysPost> wrapper = new EntityWrapper<>(sysPost);
        Page<SysPost> pageObj = isysPostService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysPost:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysPost sysPost){
        boolean b = isysPostService.insert(sysPost);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysPost:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysPostService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysPost:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysPost sysPost){
        boolean b = isysPostService.updateById(sysPost);
        return new ResultInfo<>(b);
    }

}

