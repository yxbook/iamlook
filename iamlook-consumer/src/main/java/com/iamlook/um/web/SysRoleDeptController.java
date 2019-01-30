package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.iamlook.um.dto.ResultInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import com.iamlook.um.entity.SysRoleDept;
import com.iamlook.um.service.ISysRoleDeptService;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 角色和部门关联表 前端控制器
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Controller
@RequestMapping("/sysRoleDept")
public class SysRoleDeptController extends BaseController {

    @Reference(version = "1.0.0")
    private ISysRoleDeptService isysRoleDeptService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/listData")
    @RequiresPermissions("sysRoleDept:view")
    public @ResponseBody
        ResultInfo<List<SysRoleDept>> listData(SysRoleDept sysRoleDept, Integer page, Integer limit){
        EntityWrapper<SysRoleDept> wrapper = new EntityWrapper<>(sysRoleDept);
        Page<SysRoleDept> pageObj = isysRoleDeptService.selectPage(new Page<>(page,limit), wrapper);
        return new ResultInfo<>(pageObj.getRecords(), pageObj.getTotal());
    }

    @RequestMapping("/add")
    @RequiresPermissions("sysRoleDept:add")
    public @ResponseBody
        ResultInfo<Boolean> add(SysRoleDept sysRoleDept){
        boolean b = isysRoleDeptService.insert(sysRoleDept);
        return new ResultInfo<>(b);
    }

    @RequestMapping("/delBatch")
    @RequiresPermissions("sysRoleDept:del")
    public @ResponseBody
        ResultInfo<Boolean> delBatch(Integer[] idArr){
        boolean b = isysRoleDeptService.deleteBatchIds(Arrays.asList(idArr));
        return new ResultInfo<>(b);
    }

    @RequestMapping("/edit")
    @RequiresPermissions("sysRoleDept:edit")
    public @ResponseBody
        ResultInfo<Boolean> edit(SysRoleDept sysRoleDept){
        boolean b = isysRoleDeptService.updateById(sysRoleDept);
        return new ResultInfo<>(b);
    }

}

