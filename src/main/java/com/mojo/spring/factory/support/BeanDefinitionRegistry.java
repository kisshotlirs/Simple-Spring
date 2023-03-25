package com.mojo.spring.factory.support;

import com.mojo.spring.factory.config.BeanDefinition;

/**
 * @author: zyl
 * @description: Bean定义注册表
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 beanDefinition
     * @param beanName 名称
     * @param beanDefinition bean的定义信息
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
