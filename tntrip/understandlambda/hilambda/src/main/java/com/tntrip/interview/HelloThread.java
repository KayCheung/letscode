package com.tntrip.interview;

import java.util.Random;

/**
 * Created by libing2 on 2016/10/12.
 */
public class HelloThread {
    public static synchronized void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                hello();
            }
        };
        t.start();

        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {

        }
        System.out.print("There");
    }

    static synchronized void hello() {
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (InterruptedException e) {

        }
        System.out.print("Hello");
    }
}
