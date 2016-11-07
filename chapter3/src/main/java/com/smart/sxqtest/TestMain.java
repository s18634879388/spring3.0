package com.smart.sxqtest;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/11/7.
 */
public class TestMain {
    public static void main(String[] args) {

//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(NativeWaiter.class);
//        NativeWaiter nativeWaiter = applicationContext.getBean(NativeWaiter.class);
//        String name = "john";
//        nativeWaiter.greetTo(name);
//        nativeWaiter.serverTo(name);



//        //example
//        BeforeAdvice beforeAdvice;
//        ProxyFactory proxyFactory;
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(NativeWaiter.class);
//        NativeWaiter nativeWaiter = applicationContext.getBean(NativeWaiter.class);
//        beforeAdvice = new GreetBeforeAdvice();
//        //spring提供的代理工厂
//        proxyFactory = new ProxyFactory();
//        //设置代理目标
//        proxyFactory.setTarget(nativeWaiter);
//        //为代理目标添加增强
//        proxyFactory.addAdvice(beforeAdvice);
//        //测试。。。。
//        String name = "john";
//        Waiter proxy = (Waiter) proxyFactory.getProxy();
//        proxy.greetTo(name);
//        proxy.serverTo(name);


        //spring配置的方式
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/smart/sxqtest/beans.xml");
//        Waiter waiter = (Waiter) applicationContext.getBean("waiter");
//        String name = "john";
//        waiter.greetTo(name);
//        waiter.serverTo(name);



    }
}
