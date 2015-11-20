package com.tuniu.understand.targettyping;

import java.security.PrivilegedAction;
import java.util.concurrent.Callable;

/**
 * Created by nuc on 2015/11/20.
 */
public class OverloadMethod {
    public void overloadMethod(Callable<String> c) {

    }

    public void overloadMethod(PrivilegedAction<String> pa) {

    }

    public static void main(String[] args) {
        OverloadMethod om = new OverloadMethod();

        Callable<String> c = () -> "Done";
        om.overloadMethod(c);

        PrivilegedAction<String> pa = () -> "Done";
        om.overloadMethod(pa);

        // Both m1(A) and m1(B) match
        // om.overloadMethod(() -> "bong");

        om.overloadMethod((Callable<String>) () -> "bong");
        om.overloadMethod((PrivilegedAction<String>) () -> "bong");

    }
}
