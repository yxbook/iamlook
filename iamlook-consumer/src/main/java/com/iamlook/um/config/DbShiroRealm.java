package com.iamlook.um.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iamlook.um.query.LoginUser;
import com.iamlook.um.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DbShiroRealm extends AuthorizingRealm {

	private final Logger LOGGER = LoggerFactory.getLogger(DbShiroRealm.class);
	
	private static final String encryptSalt = "F12839WhsnnEV$#23b";

	@Reference(version = "1.0.0")
	private ISysUserService iSysUserService;

	public DbShiroRealm() {
		this.setCredentialsMatcher(new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME));
	}
	
	@Override
    public boolean supports(AuthenticationToken token) {
		System.err.println("---DbShiroRealm--supports----------------");
		System.err.println(token instanceof UsernamePasswordToken);
		System.err.println("---DbShiroRealm--supports----------------");
		return token instanceof UsernamePasswordToken;
    }
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.err.println("---DbShiroRealm--doGetAuthenticationInfo----------------");
		UsernamePasswordToken userpasswordToken = (UsernamePasswordToken)token;
		String username = userpasswordToken.getUsername();

		//从数据库中获取用户信息,这里写死
		LoginUser user = iSysUserService.getUserInfo(username);
		user.setEncryptPwd(new Sha256Hash("123456", encryptSalt).toHex());

		if(user == null)
			throw new AuthenticationException("用户名或者密码错误");
		
		return new SimpleAuthenticationInfo(user, user.getEncryptPwd(), ByteSource.Util.bytes(encryptSalt), "dbRealm");
	}


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		System.err.println("---DbShiroRealm--doGetAuthorizationInfo----------------");

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		LoginUser user = (LoginUser) principals.getPrimaryPrincipal();
        List<String> roles = user.getRoles();
        if(roles == null) {
        	//获取用户角色列表，强烈建议从缓存中获取,写死先
            roles = iSysUserService.getUserRoles(user.getUserId());
            user.setRoles(roles);
        }
        if (roles != null)
            simpleAuthorizationInfo.addRoles(roles);

        return simpleAuthorizationInfo;
	}

	
}
