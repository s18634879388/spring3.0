package com.smart.sxqtest;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/3.
 */
@Component
public class TestB {
    private String bname;
    private int bage;

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getBage() {
        return bage;
    }

    public void setBage(int bage) {
        this.bage = bage;
    }
}
