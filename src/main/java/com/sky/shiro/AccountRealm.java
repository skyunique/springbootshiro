package com.sky.shiro;

import com.sky.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        AccountProfile profile = (AccountProfile) principalCollection.getPrimaryPrincipal();

        // 硬编码（赋予用户权限或角色）
        if(profile.getUserName().equalsIgnoreCase("admin")){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("admin");
            return simpleAuthorizationInfo;
        }

        return null;
    }


    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        AccountProfile profile = userService.login(usernamePasswordToken.getUsername(), String.valueOf(usernamePasswordToken.getPassword()));
        //把用户信息存到session中，方便前端显示
        SecurityUtils.getSubject().getSession().setAttribute("profile",profile);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(profile,authenticationToken.getCredentials(),getName());
        return info;
    }
}
