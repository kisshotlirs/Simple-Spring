package com.mojo.spring.bean;

/**
 * @author: zyl
 * @description: bean用例
 */
public class UserService {

    private String name;

    public UserService(String name){
        this.name = name;
    }

    public void queryUserInfo(){
        System.out.println("查询用户信息："+name);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("").append(name);
        return stringBuilder.toString();
    }
}
