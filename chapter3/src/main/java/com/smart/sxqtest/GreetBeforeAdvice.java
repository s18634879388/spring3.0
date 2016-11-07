package com.smart.sxqtest;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/7.
 */
public class GreetBeforeAdvice implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, Object target) throws Throwable {
        String name = (String) args[0];
        System.out.println("hello, Nice to meet you,Mr"+name);
    }
}
