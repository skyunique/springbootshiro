package com.sky.service.impl;

import com.sky.service.UserService;
import com.sky.shiro.AccountProfile;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public AccountProfile login(String username, String password) {

        //TODO 查库，然后匹配密码是否正确！
        //如下测试举例
        if(!"Sky".equals(username)) {
            // 抛出shiro异常，方便通知用户登录错误信息
            throw new UnknownAccountException("用户不存在");
        }
        if(!"111111".equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        AccountProfile profile = new AccountProfile();
        profile.setId(1L);
        profile.setUserName("Sky");
        profile.setSign("BLUE");
        return profile;
    }
}
