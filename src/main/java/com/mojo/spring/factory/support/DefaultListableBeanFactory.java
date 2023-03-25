package com.mojo.spring.factory.support;

import com.mojo.spring.BeanException;
import com.mojo.spring.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyl
 * @description: 默认列表 bean的实例化工厂
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 获取bean的定义信息
     */
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null){
            throw new BeanException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    /**
     * 向注册表中注册 beanDefinition
     * @param beanName 名称
     * @param beanDefinition bean的定义信息
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }
}
