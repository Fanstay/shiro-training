package com.liuxl.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @Author: liuxl
 * @Date: 2019/4/11 22:41
 * @Version 1.0
 */
public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();//得到用户名
        String password = new String((char[])authenticationToken.getCredentials());//得到密码
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();//如果用户名错误
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();//密码错误
        }
        //如果身份认证成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
