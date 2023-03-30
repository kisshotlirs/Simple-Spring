package com.mojo.spring.bean.factory.config;

/**
 * @author: zyl
 * @description: bean的引用
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName(){
        return beanName;
    }
}
