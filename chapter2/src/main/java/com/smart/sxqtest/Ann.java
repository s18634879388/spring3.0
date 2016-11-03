package com.smart.sxqtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/3.
 */
@Component
public class Ann {
    @Autowired
    TestB testB;
    public void ann(){
        testB.setBname("qiulimaosi");
        System.out.println(testB.getBname());
    }
}
