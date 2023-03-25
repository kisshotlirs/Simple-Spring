package com.mojo.spring.factory.support;

import com.mojo.spring.BeanException;
import com.mojo.spring.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author: zyl
 * @description: 抽象 bean的实例化工厂
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 根据bean的定义信息实例化单例bean
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition,beanName,args);
        } catch (Exception e){
            throw new BeanException("bean实例化失败", e);
        }

        addSingleton(beanName,bean);
        return bean;
    }

    /**
     * 创建对象的实例化策略属性类
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if (args != null && ctor.getParameterTypes().length == args.length){
                constructor = ctor;
                break;
            }
        }
        //使用策略模式，调用时选择使用jdk或cglib实例化 这里使用的是cglib
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
