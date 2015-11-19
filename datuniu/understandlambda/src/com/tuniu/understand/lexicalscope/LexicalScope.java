package com.tuniu.understand.lexicalscope;

import java.util.function.Function;

/**
 * Created by nuc on 2015/11/18.
 */
public class LexicalScope {

    private final Runnable lambda1 = () -> {
        System.out.println(this); // 指的是 enclosing object
    };
    private final Runnable lambda2 = () -> {
        System.out.println(toString()); // 指的是 enclosing object的方法
    };


    private final Runnable inner1 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this); // 指的是 匿名内部类（实现 Runnable的这个 没有名字的类的实例）
        }
    };

    private final Runnable inner2 = new Runnable() {
        @Override
        public void run() {
            System.out.println(toString()); // 即，this.toString()
            // 匿名内部类 实现了 Runnable，同时，这是一个类，所以，也继承自 Object，
            // 所以，也有各种 toString(), wait等方法。
            // 所以，上面对 toString()的调用：
            // 其实是 匿名内部类inherit Object's toString() shadow了 enclosing env的toString()
        }
    };

    public void wontShadow() {
        String hw = "Hello World!";
        for (int i = 0; i < 10; i++) {
            // String hw = "";
            System.out.println(hw);
        }
        // 就和 for 块中 不能 shadow enclosing env中的变量一样
        // lambda中（包括lambda的参数）都不能 shadow enclosing env中的变量
        Function<String, Integer> func = (para) -> {
            // String hw = "it's my world!!!";
            System.out.println(para);
            return para.length();
        };

        System.out.println(func.apply("abcd"));

    }

    @Override
    public String toString() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        new LexicalScope().lambda1.run();
        new LexicalScope().lambda2.run();
        new LexicalScope().inner1.run();
        new LexicalScope().inner2.run();
        new LexicalScope().wontShadow();
    }
}
