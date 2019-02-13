package com.iamlook.um.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.iamlook.um.entity.SysUser;
import com.iamlook.um.mapper.SysUserMapper;
import com.iamlook.um.query.LoginUser;
import com.iamlook.um.service.ISysUserService;
import com.iamlook.um.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 清除token信息
     * @param username 登录用户名
     */
    public void deleteLoginInfo(String username) {
        /**
         * @todo 删除数据库或者缓存中保存的salt
         * redisTemplate.delete("token:"+username);
         */

    }

    /**
     * 获取数据库中保存的用户信息，主要是加密后的密码
     * @param userName
     * @return
     */
    public SysUser getUserInfo(String userName) {
        SysUser puser = new SysUser();
        puser.setLoginName(userName);
        return sysUserMapper.selectOne(puser);
    }

    /**
     * 获取用户角色列表，强烈建议从缓存中获取
     * @param userId
     * @return
     */
    public List<String> getUserRoles(Integer userId){
        return Arrays.asList("admin");
    }

}
