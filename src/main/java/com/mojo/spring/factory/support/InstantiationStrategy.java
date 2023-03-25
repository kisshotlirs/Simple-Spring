package com.mojo.spring.factory.support;

import com.mojo.spring.BeanException;
import com.mojo.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author: zyl
 * @description: 实例化策略接口
 */
public interface InstantiationStrategy {

    /**
     * bean的实例化
     * @param beanDefinition bean的定义信息
     * @param beanName 名称
     * @param ctor 构造器类，为了拿到对应的构造函数
     * @param args 入参
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeanException;
}
