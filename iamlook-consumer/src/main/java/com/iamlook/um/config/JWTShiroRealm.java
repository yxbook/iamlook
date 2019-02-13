package com.iamlook.um.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.iamlook.um.entity.SysUser;
import com.iamlook.um.query.LoginUser;
import com.iamlook.um.service.ISysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自定义身份认证
 * 基于HMAC（ 散列消息认证码）的控制域
 */

public class JWTShiroRealm extends AuthorizingRealm {

	private final Logger LOGGER = LoggerFactory.getLogger(JWTShiroRealm.class);

	@Reference(version = "1.0.0")
    protected ISysUserService iSysUserService;

    public JWTShiroRealm(){
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        JWTToken jwtToken = (JWTToken) authcToken;
        String token = jwtToken.getToken();
        //获取上次token生成时的salt值和登录用户信息
        String username = JwtUtils.getUsername(token);
        SysUser puser = new SysUser();
        puser.setLoginName(username);
        EntityWrapper<SysUser> wrapper = new EntityWrapper<SysUser>();
        wrapper.setEntity(puser);
        SysUser sysUser = iSysUserService.selectOne(wrapper);
        LoginUser user = new LoginUser();
        user.setUserId(sysUser.getUserId());
        user.setUsername(username);
        user.setSalt(sysUser.getSalt());
        user.setPassword(sysUser.getPassword().toCharArray());
        if(user == null)
            throw new AuthenticationException("token过期，请重新登录");

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getSalt(), "jwtRealm");

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }
}
