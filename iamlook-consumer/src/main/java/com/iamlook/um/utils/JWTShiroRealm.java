package com.iamlook.um.utils;

import com.alibaba.dubbo.config.annotation.Reference;
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

    public JWTShiroRealm(ISysUserService iSysUserService){
        this.iSysUserService = iSysUserService;
        this.setCredentialsMatcher(new JWTCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        LOGGER.info("Adeep----------------JWTShiroRealm---------------supports");
        return token instanceof JWTToken;
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        LOGGER.info("Adeep----------------JWTShiroRealm---------------doGetAuthenticationInfo");

        JWTToken jwtToken = (JWTToken) authcToken;
        String token = jwtToken.getToken();

        //获取上次token生成时的salt值和登录用户信息
        LoginUser user = iSysUserService.getJwtTokenInfo(JwtUtils.getUsername(token));

        if(user == null)
            throw new AuthenticationException("token过期，请重新登录");

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getSalt(), "jwtRealm");

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {


        LOGGER.info("Adeep----------------JWTShiroRealm---------------doGetAuthorizationInfo肉丝");

        return new SimpleAuthorizationInfo();
    }
}
