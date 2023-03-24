package com.mojo.spring.factory;

import com.mojo.spring.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zyl
 * @description: bean对象工厂
 */
public interface BeanFactory {

    Object getBean(String name);
}
