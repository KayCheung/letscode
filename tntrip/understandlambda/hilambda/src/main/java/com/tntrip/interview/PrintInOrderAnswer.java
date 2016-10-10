package com.tntrip.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by libing2 on 2016/10/9.
 */
public class PrintInOrderAnswer {
    private final Object lock = new Object();
    private Map<String, Boolean> shouldPrint = new HashMap<>();

    private Thread threadA = new Thread("thread-a") {
        @Override
        public void run() {
            printNowThenGoNext("A", "B");
        }
    };

    private Thread threadB = new Thread("thread-b") {
        @Override
        public void run() {
            printNowThenGoNext("B", "C");
        }
    };

    private Thread threadC = new Thread("thread-c") {
        @Override
        public void run() {
            printNowThenGoNext("C", null);
        }
    };

    private void printNowThenGoNext(final String strNow, final String strNext) {
        long begin = System.nanoTime();
        synchronized (lock) {
            while (!shouldPrint.get(strNow)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(strNow);
            if (strNext != null) {
                shouldPrint.put(strNext, true);
            }
            lock.notifyAll();
        }
        System.out.println(Thread.currentThread().getName() + " cost: " + (System.nanoTime() - begin));
    }

    public void startAll() {
        shouldPrint.put("A", true);
        shouldPrint.put("B", false);
        shouldPrint.put("C", false);
        //要求：
        // 1. 无论 线程的启动顺序 如何，都要保证 输出的是 ABC
        // 2. 不要通过 “让线程sleep不同的时间段” 来实现。即不要通过：threadC.sleep(5000), threadB.sleep(3000), threadA.sleep(1000) 来实现
        List<Thread> threads = Arrays.asList(threadB, threadC, threadA);
        for (Thread t : threads) {
            t.start();
        }
    }

    public static void main(String[] args) {
        new PrintInOrderAnswer().startAll();
    }
}
