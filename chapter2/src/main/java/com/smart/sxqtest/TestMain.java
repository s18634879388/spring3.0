package com.smart.sxqtest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/3.
 */
@Component
public class TestMain {
    @Autowired
    TestB testB;
    public static void main(String[] args) {
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        Resource resource = resourcePatternResolver.getResource("classpath:com/smart/sxqtest/beans.xml");
//        BeanFactory beanFactory = new XmlBeanFactory(resource);
//        TestA testA = beanFactory.getBean("sxq",TestA.class);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/smart/sxqtest/*.xml");
//        TestA testA = applicationContext.getBean("sxq",TestA.class);
//        testA.setName("99933");

//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Beans.class);
//        TestA testA = applicationContext.getBean("testa",TestA.class);
//        testA.setName("44444");
//        System.out.println(testA.getName());
//        Ann ann = applicationContext.getBean(Ann.class);
//        ann.ann();
        TestMain testMain = applicationContext.getBean(TestMain.class);
        testMain.ann2();
    }
    public void ann2(){
        testB.setBname("qiuqiuqiu");
        System.out.println(testB.getBname());
    }
}
