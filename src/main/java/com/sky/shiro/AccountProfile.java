package com.sky.shiro;

import lombok.Data;

import java.io.Serializable;

@Data //使用lombok简易化 getter、setter方法
public class AccountProfile implements Serializable {
    //实现序列化

    private Long id;

    private String userName;

    private String gender;

    private String sign;
}
