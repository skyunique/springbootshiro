package com.sky.config;

import com.sky.shiro.AccountRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {


    @Bean
    AccountRealm accountRealm(){
        return new AccountRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/admin/**","authc,roles[admin]");


        chainDefinition.addPathDefinition("/docs/**","authc,perms[document:read]");

        chainDefinition.addPathDefinition("/login","anon");

        chainDefinition.addPathDefinition("/doLogin","anon");

        chainDefinition.addPathDefinition("/**","authc");

        return chainDefinition;
    }
}