package com.smart.service;

import com.smart.domain.User;
import com.smart.domain.ViewPoint;
import com.smart.domain.ViewSpace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.spring.annotation.SpringBeanByType;

@SpringApplicationContext({"classpath:applicationContext-tx.xml"})
public class ViewSpaceTxTest extends UnitilsTestNG {
    @SpringBeanByType
    private IViewSpace viewSpaceService;

    @Test
    public void addViewSpace() throws Throwable {
        System.out.println("begin........");
        ViewSpace vs = new ViewSpace();
        vs.setSpaceName("space name!");
        User u = new User();
        u.setUserId(1);
        vs.setUser(u);
        viewSpaceService.addViewSpace(vs);
        System.out.println("end........");
    }
}
