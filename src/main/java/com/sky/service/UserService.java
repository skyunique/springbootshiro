package com.sky.service;

import com.sky.shiro.AccountProfile;

public interface UserService {

    AccountProfile login(String username, String password);
}
