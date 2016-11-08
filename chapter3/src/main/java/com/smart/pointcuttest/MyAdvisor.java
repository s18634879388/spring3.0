package com.smart.pointcuttest;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * Created by shixiaoqi on 2016/11/8.
 */
public class MyAdvisor extends StaticMethodMatcherPointcutAdvisor{

    public boolean matches(Method method, Class<?> aClass) {

        return "greet".equals(method.getName());
    }
    public ClassFilter getClassFilter(){
        return new ClassFilter() {
            public boolean matches(Class<?> aClass) {
                return NativeWaiter2.class.isAssignableFrom(aClass);
            }
        };
    }
}
