package com.smart.pointcuttest;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shixiaoqi on 2016/11/8.
 */
public class TestMain {
    public static void main(String[] args) {

/*
        //advisior。。。
        NativeServer2 nativeServer2 = new NativeServer2();
        NativeWaiter2 nativeWaiter2 = new NativeWaiter2();
        StaticMethodMatcherPointcutAdvisor advisor = new MyAdvisor();
        MethodBeforeAdvice advice = new MyAdvice();
        ProxyFactory proxyFactory = new ProxyFactory();
        advisor.setAdvice(advice);
        proxyFactory.setTarget(nativeWaiter2);
//        proxyFactory.addAdvice(advice);
        proxyFactory.addAdvisor(advisor);
        NativeWaiter2 nativeWaiter21 = (NativeWaiter2) proxyFactory.getProxy();
        String name = "Tom";
        nativeWaiter21.greet(name);
        nativeWaiter21.service(name);*/

        //自动创建代理
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/smart/pointcuttest/*.xml");
        NativeWaiter2 nativeWaiter2 = (NativeWaiter2) applicationContext.getBean("waiter2");
        nativeWaiter2.greet("hehe");



    }
}
