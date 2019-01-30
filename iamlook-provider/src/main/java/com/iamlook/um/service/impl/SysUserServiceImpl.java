package com.iamlook.um.service.impl;

import com.iamlook.um.entity.SysUser;
import com.iamlook.um.mapper.SysUserMapper;
import com.iamlook.um.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Auto Generator
 * @since 2019-01-30
 */
@Service(version = "1.0.0", timeout = 60000)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
