package com.mojo.spring.bean.factory.support;

import com.mojo.spring.bean.BeanException;
import com.mojo.spring.bean.factory.BeanFactory;
import com.mojo.spring.bean.factory.config.BeanDefinition;

/**
 * @author: zyl
 * @description: 抽象bean对象工厂
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name, Object... args) {
        //尝试获取单例bean
        Object bean = getSingleton(name);
        if (bean != null){
            return bean;
        }

        //获取不到单例bean，拿到bean的定义做相应bean的实例化操作
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition,args);
    }

    @Override
    public Object getBean(String name) throws BeanException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition,null);
    }

    /**
     * 获取bean的定义
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    /**
     * 根据bean的定义实例化单例bean
     */
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeanException;
}
