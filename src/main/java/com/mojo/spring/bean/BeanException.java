package com.mojo.spring.bean;

/**
 * @author: zyl
 * @description: 异常处理
 */
public class BeanException extends RuntimeException{

    public BeanException(String msg){
        super(msg);
    }

    public BeanException(String msg,Throwable cause){
        super(msg,cause);
    }
}
