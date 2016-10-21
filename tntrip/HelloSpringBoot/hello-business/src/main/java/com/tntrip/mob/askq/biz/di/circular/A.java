package com.tntrip.mob.askq.biz.di.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by libing2 on 2016/10/20.
 */
@Component

public class A {
    private B b;

    public A() {
        System.out.println("No paramter");
    }
    @Autowired
    public A(B b, B b1) {
        System.out.println("2 parameters");
    }

    @Autowired
    public A(B b2, B b, B b3) {
        System.out.println("3 parameters");
    }

    public A(B b) {
        System.out.println("1 parameters");
        this.b = b;
        System.out.println("A is fully constructed. Already injected b into a...");
    }
}
