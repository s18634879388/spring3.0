package com.smart.sxqtest;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/4.
 */
@Component
public class TestC {
    private String cname;
    private int cage;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCage() {
        return cage;
    }

    public void setCage(int cage) {
        this.cage = cage;
    }
}
