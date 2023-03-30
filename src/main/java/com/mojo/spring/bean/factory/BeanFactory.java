package com.mojo.spring.bean.factory;

import com.mojo.spring.bean.BeanException;

/**
 * @author: zyl
 * @description: bean对象工厂
 */
public interface BeanFactory {

    Object getBean(String name) throws BeanException;

    /**
     * 获取 Bean 时把构造函数的入参信息传递进去
     */
    Object getBean(String name,Object... args) throws BeanException;
}
