package com.tuniu.understand.rule4default;

/**
 * Created by nuc on 2015/11/17.
 */
interface A0 {
    void mA();

    default void mB() {
        System.out.println("A0.mB");
    }
}

interface A1 extends A0 {
    void mA();

    default void mB() {
        System.out.println("A1.mB");
    }
}

interface A2 extends A0 {
    void mA();

//    default void mB() {
//        System.out.println("A2.mB");
//    }
}

public class AImpl implements A1, A2 {
    @Override
    public void mA() {
        System.out.println("AImpl.mA");
    }

    public static void main(String[] args) {
        A0 a = new AImpl();
        // 那些被覆盖过的方法，将被 抛弃
        // A0.mB 别覆盖过了，是被A1.mB覆盖的。所以，A0.mB 被抛弃了
        // 如果A2也覆盖下 A0.mB，则，AImpl 就不知道选谁了。编译错误
        a.mB();
    }
}
