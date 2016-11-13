package com.smart.pointcuttest;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;



/**
 * Created by Administrator on 2016/11/13.
 */
@Aspect
public class AspectJTest {
    @Before("execution(* *greet(..)) && args(name)")
    public void aspectjTest(String name){
        System.out.println("hello,Mr"+name);
    }
    @AfterReturning(value = "execution(* *greet(..))")
    public void after(){
        System.out.println("**********************");
    }
}
