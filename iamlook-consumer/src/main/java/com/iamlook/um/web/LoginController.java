package com.iamlook.um.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iamlook.um.query.LoginUser;
import com.iamlook.um.service.ISysUserService;
import com.iamlook.um.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/iamlook")
public class LoginController {
	
	private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Reference(version = "1.0.0")
    private ISysUserService isysUserService;

    /**
     * 用户名密码登录
     * @param request
     * @return token
     */
    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody LoginUser loginUser, HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(), loginUser.getPassword());
            subject.login(token);

            LoginUser user = (LoginUser) subject.getPrincipal();
            //生成jwt token，设置过期时间为1小时
            //JwtUtils.generateSalt()
            String salt = "12345";

            String jwtToken = JwtUtils.sign(user.getUsername(), salt, 3600);
            /*
            *
            *将salt保存到数据库或者缓存中
            * */
            //isysUserService.saveJwtToken(jwtToken);


            response.setHeader("x-auth-token", jwtToken);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping(value = "/logout")
    public ResponseEntity<Void> logout() {
    	Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipals() != null) {
            LoginUser user = (LoginUser)subject.getPrincipals().getPrimaryPrincipal();
            //清除数据库中的token信息
            isysUserService.deleteLoginInfo(user.getUsername());
        }
        SecurityUtils.getSubject().logout();
        return ResponseEntity.ok().build();
    }

}
