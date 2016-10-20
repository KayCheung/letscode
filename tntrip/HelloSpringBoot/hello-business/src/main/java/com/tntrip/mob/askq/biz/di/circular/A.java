package com.tntrip.mob.askq.biz.di.circular;

import org.springframework.stereotype.Component;

/**
 * Created by libing2 on 2016/10/20.
 */
@Component
public class A {
    private B b;

    public A(B b) {
        this.b = b;
        System.out.println("A is fully constructed. Already injected b into a...");
    }
}
