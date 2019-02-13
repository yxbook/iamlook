package com.iamlook.um.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 登录对象
 */
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = -9077975168976887742L;

    private String username;
    private char[] password;
    private String encryptPwd;
    private Long userId;
    private String salt;
    private List<String> roles;


}
