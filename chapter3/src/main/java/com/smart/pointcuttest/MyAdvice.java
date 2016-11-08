package com.smart.pointcuttest;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by shixiaoqi on 2016/11/8.
 */
public class MyAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String name = (String) objects[0];
        System.out.println(" 。。。。"+name+" method name "+method.getName()+" class name "+o.getClass().getName());
    }
}
