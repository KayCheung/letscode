package com.tuniu.understand.rule4default;

/**
 * Created by nuc on 2015/11/17.
 */
interface C {
    void mA();

    default void mB() {
        System.out.println("C.mB");
    }
}

abstract class CImpl implements C {
    @Override
    public void mA() {
        System.out.println("CImpl.mA");
    }

    public abstract void mB();

}

public class ClassMethodPreferred extends CImpl {
    @Override
    public void mA() {
        System.out.println("ClassMethodPreferred.mA");
    }

    // class中 同样签名的方法（即使是abstract的），总是优于 接口的default method
    // Hence the default keyword: default methods are a fallback if the class hierarchy doesn't say anything
    @Override
    public void mB() {
        System.out.println("ClassMethodPreferred.mB");
    }

    public static void main(String[] args) {
        C c = new ClassMethodPreferred();
        c.mB();
    }
}
