package com.mojo.spring.factory.config;

/**
 * @author: zyl
 * @description: 获取单例对象接口
 */
public interface SingletonBeanRegistry {

    /**
     * 获取单例bean对象
     * @param beanName bean名称
     */
    Object getSingleton(String beanName);
}
