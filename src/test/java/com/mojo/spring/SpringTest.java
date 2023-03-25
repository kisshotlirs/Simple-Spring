package com.mojo.spring;

import com.mojo.spring.bean.UserService;
import com.mojo.spring.factory.BeanFactory;
import com.mojo.spring.factory.config.BeanDefinition;
import com.mojo.spring.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

/**
 * @author: zyl
 * @description: 测试
 */
public class SpringTest {

    @Test
    public void test(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }
}
