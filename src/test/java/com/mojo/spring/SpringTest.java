package com.mojo.spring;

import com.mojo.spring.bean.UserDao;
import com.mojo.spring.bean.UserServer;
import com.mojo.spring.bean.UserService;
import com.mojo.spring.bean.factory.PropertyValue;
import com.mojo.spring.bean.factory.PropertyValues;
import com.mojo.spring.bean.factory.config.BeanDefinition;
import com.mojo.spring.bean.factory.config.BeanReference;
import com.mojo.spring.bean.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

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

    @Test
    public void test2(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService","mojo");
        userService.queryUserInfo();
    }

    @Test
    public void test_newInstance() throws InstantiationException, IllegalAccessException {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws Exception{
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(String.class);
        UserService instance = declaredConstructor.newInstance("mojo");
        System.out.println(instance);
    }

    @Test
    public void test_parameterTypes() throws Exception{
        Class<UserService> userServiceClass = UserService.class;

        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        Constructor<?> constructor = declaredConstructors[0];

        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(constructor.getParameterTypes());
        UserService us = declaredConstructor.newInstance("mojo");
        System.out.println(us);
    }

    @Test
    public void test_cglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object obj = enhancer.create(new Class[]{String.class}, new Object[]{"mojo"});
        System.out.println(obj);
    }

    @Test
    public void test_beanFactory(){
        //初始化
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        //userDao注册
        factory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));

        //userServer设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId","1"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        //userServer注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserServer.class, propertyValues);
        factory.registerBeanDefinition("userServer",beanDefinition);

        //userServer获取bean
        UserServer userServer = (UserServer) factory.getBean("userServer");
        userServer.queryUserInfo();
    }
}
