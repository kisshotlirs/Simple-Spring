package com.mojo.spring.factory.support;

import com.mojo.spring.factory.BeanFactory;

/**
 * @author: zyl
 * @description: 抽象bean对象工厂
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) {
        Object bean = getSingleton(name);
        if (bean != null){
            return bean;
        }

        return null;
    }
}
