package com.smart.sxqtest;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ThrowExceptionAdvice implements ThrowsAdvice{
    public void afterThrowing(Method method,Object[] args,Object target,Throwable throwable ){

    }

}
