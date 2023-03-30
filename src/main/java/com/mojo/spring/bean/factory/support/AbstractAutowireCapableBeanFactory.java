package com.mojo.spring.bean.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.mojo.spring.bean.BeanException;
import com.mojo.spring.bean.factory.PropertyValue;
import com.mojo.spring.bean.factory.PropertyValues;
import com.mojo.spring.bean.factory.config.BeanReference;
import com.mojo.spring.bean.factory.config.BeanDefinition;

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
            //给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
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

    /**
     * bean属性填充
     */
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                Object value = propertyValue.getValue();
                String name = propertyValue.getName();

                if (value instanceof BeanReference){
                    //a依赖b，获取b的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                //属性填充
                BeanUtil.setFieldValue(bean,name,value);
            }
        } catch (Exception e){
            throw new BeanException("Bean属性填充错误：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
