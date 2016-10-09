package com.tntrip.interview;

import java.util.Arrays;
import java.util.List;

/**
 * Created by libing2 on 2016/10/9.
 */
public class PrintInOrder {
    
    private Thread threadA = new Thread("A") {
        @Override
        public void run() {
            System.out.println("A");
        }
    };
    private Thread threadB = new Thread("B") {
        @Override
        public void run() {
            System.out.println("B");
        }
    };
    private Thread threadC = new Thread("C") {
        @Override
        public void run() {
            System.out.println("C");
        }
    };

    public void startAll() {
        List<Thread> threads = Arrays.asList(threadB, threadC, threadA);
        for (Thread t : threads) {
            t.start();
        }
    }

    public static void main(String[] args) {
        new PrintInOrder().startAll();
    }
}
