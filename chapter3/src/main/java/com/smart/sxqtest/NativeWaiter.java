package com.smart.sxqtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/11/7.
 */
@Configuration
public class NativeWaiter implements Waiter{
//    @Bean
//    public NativeWaiter getWaiter(){
//        return new NativeWaiter();
//    }

    public void greetTo(String name) {
        System.out.println("greet to "+name+".....");
    }

    public void serverTo(String name) {
        System.out.println("server to "+name+".......");
    }

}
