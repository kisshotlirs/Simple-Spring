package com.mojo.spring.factory.support;

import com.mojo.spring.BeanException;
import com.mojo.spring.factory.config.BeanDefinition;

/**
 * @author: zyl
 * @description: 抽象 bean的实例化工厂
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * 根据bean的定义信息实例化单例bean
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e){
            throw new BeanException("bean实例化失败", e);
        }

        addSingleton(beanName,bean);
        return bean;
    }
}
