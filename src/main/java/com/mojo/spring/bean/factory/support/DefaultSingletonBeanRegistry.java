package com.mojo.spring.bean.factory.support;

import com.mojo.spring.bean.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyl
 * @description: 默认单例bean注册
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 单例对象集合
     */
    private Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 添加单例bean对象 声名为protected，可以被继承此类的其他类调用
     * @param beanName bean名称
     * @param singleObject bean对象
     */
    protected void addSingleton(String beanName,Object singleObject){
        singletonObjects.put(beanName, singleObject);
    }
}
