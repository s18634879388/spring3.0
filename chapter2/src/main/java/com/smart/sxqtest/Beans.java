package com.smart.sxqtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/11/3.
 */
@Configuration
public class Beans {
    @Bean(name="testa")
    public TestA getA(){
        TestA t = new TestA();
        return t;
    }
}
