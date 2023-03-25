package com.mojo.spring.factory;

import com.mojo.spring.BeanException;
import com.mojo.spring.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zyl
 * @description: bean对象工厂
 */
public interface BeanFactory {

    Object getBean(String name) throws BeanException;

    /**
     * 获取 Bean 时把构造函数的入参信息传递进去
     */
    Object getBean(String name,Object... args) throws BeanException;
}
