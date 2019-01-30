package com.iamlook.um.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.iamlook.um.entity.SysUser;
import com.iamlook.um.mapper.SysUserMapper;
import com.iamlook.um.query.LoginUser;
import com.iamlook.um.service.ISysUserService;

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

    /**
     * 保存user登录信息，返回token
     */
    public int saveJwtToken(String username) {
        /**
         * @todo 将salt保存到数据库或者缓存中
         * redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
         */
        return 1; //生成jwt token，设置过期时间为1小时
    }

    /**
     * 获取上次token生成时的salt值和登录用户信息
     * @param username
     * @return
     */
    public LoginUser getJwtTokenInfo(String username) {
        String salt = "12345";
        /**
         * @todo 从数据库或者缓存中取出jwt token生成时用的salt
         * salt = redisTemplate.opsForValue().get("token:"+username);
         */
        LoginUser user = getUserInfo(username);
        user.setSalt(salt);
        return user;
    }

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
    public LoginUser getUserInfo(String userName) {
        LoginUser user = new LoginUser();
        user.setUserId(1L);
        user.setUsername("admin");
        return user;
    }

    /**
     * 获取用户角色列表，强烈建议从缓存中获取
     * @param userId
     * @return
     */
    public List<String> getUserRoles(Long userId){
        return Arrays.asList("admin");
    }

}
