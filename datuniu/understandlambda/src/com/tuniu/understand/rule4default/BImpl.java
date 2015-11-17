package com.tuniu.understand.rule4default;

/**
 * Created by nuc on 2015/11/17.
 */
interface X {
    void mA();

    default void mB() {
        System.out.println("X.mB");
    }
}

interface Y {
    void mA();

    default void mB() {
        System.out.println("Y.mB");
    }
}

class XYImpl implements X, Y {
    @Override
    public void mA() {
        System.out.println("XYImpl.mA");
    }

    // 实现类必须要再次实现下 重复的default方法，因为 XYImpl 糊涂了，它不知道 X Y中谁更合适
    // 为 default 而专门增强的 super语法，可以让你选择 某个特定 父接口中的 默认实现
    // 在extends语句中 interface定义的顺序，或 接口被实现的顺序（先被实现的，最近才被实现的）并不影响到 对 default 方法的选择
    // In no case does the order in which interfaces are declared in an inherits or extends clause, or which interface was implemented "first" or "more recently", affect inheritance.
    @Override
    public void mB() {
        X.super.mB();
    }
}

public class BImpl {

}
