package com.mojo.spring.factory.support;

import com.mojo.spring.BeanException;
import com.mojo.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: zyl
 * @description: jdk实例化
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeanException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if (ctor != null){
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return beanClass.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeanException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
