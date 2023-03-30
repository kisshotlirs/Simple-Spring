package com.mojo.spring.bean;

import lombok.Data;

/**
 * @author: zyl
 * @description:
 */
@Data
public class UserServer {

    private String userId;

    private UserDao userDao;

    public void queryUserInfo(){
        System.out.println("查询用户信息："+userDao.queryUserName(userId));
    }
}
