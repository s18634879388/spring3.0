package com.smart.sxqtest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by Administrator on 2016/11/7.
 */
public class GreetAroundAdvice implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] args = invocation.getArguments();
        String name = (String) args[0];
        System.out.println("你好"+name);
        Object object = invocation.proceed();
        System.out.println("再见");
        return object;
    }
}
