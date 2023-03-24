package com.mojo.spring.factory.config;

/**
 * @author: zyl
 * @description: 定义bean的实例化信息
 */
public class BeanDefinition {

    private Class bean;

    public BeanDefinition(Class bean) {
        this.bean = bean;
    }

    public Class getBean() {
        return bean;
    }

    public void setBean(Class bean) {
        this.bean = bean;
    }
}
