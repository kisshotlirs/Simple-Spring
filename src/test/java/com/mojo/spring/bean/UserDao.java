package com.mojo.spring.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyl
 * @description:
 */
public class UserDao {

    private static Map<String,String> map = new HashMap<>();

    static {
        map.put("1","张三");
        map.put("2","李四");
        map.put("3","王五");
    }

    public String queryUserName(String userId){
        return map.get(userId);
    }
}
