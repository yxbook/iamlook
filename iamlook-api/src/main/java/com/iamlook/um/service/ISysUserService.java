package com.iamlook.um.service;

import com.baomidou.mybatisplus.service.IService;
import com.iamlook.um.entity.SysUser;
import com.iamlook.um.query.LoginUser;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
public interface ISysUserService extends IService<SysUser> {

    public SysUser getUserInfo(String userName);

    public List<String> getUserRoles(Integer userId);

    public void deleteLoginInfo(String username);



}
