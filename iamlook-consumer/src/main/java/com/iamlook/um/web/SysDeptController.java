package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysDept;
import com.iamlook.um.service.ISysDeptService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysDept")
public class SysDeptController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysDeptService isysDeptService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysDept:view")
    public @ResponseBody
        ResultInfo<List<SysDept>> listData(SysDept sysDept, Integer page, Integer limit){
        EntityWrapper<SysDept> wrapper = new EntityWrapper<>(sysDept);
        Page<SysDept> pageObj = isysDeptService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysDept:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysDept sysDept){
        boolean b = isysDeptService.insert(sysDept);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysDept:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysDeptService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysDept:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysDept sysDept){
        boolean b = isysDeptService.updateById(sysDept);
        return new ResultInfo<>(b);
    }

}

