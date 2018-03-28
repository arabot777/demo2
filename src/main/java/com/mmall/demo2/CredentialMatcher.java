package com.mmall.demo2;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 校验规则重写
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //token里的密码
        String password = new String(usernamePasswordToken.getPassword());
        //数据库里的密码
        String dbPassword = (String) info.getCredentials();
        return this.equals(password,dbPassword);
    }
}
