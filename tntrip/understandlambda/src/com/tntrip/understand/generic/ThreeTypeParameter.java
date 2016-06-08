package com.tntrip.understand.generic;

/**
 * Created by libing2 on 2016/6/8.
 */
public class ThreeTypeParameter<T1, T2> {
    public T1 instanceMethod(T1 a, T2 b){
        return a;
    }

    public <I1, I2, IR> IR instanceMethod(T1 a, T2 b, I1 c, I2 d, IR r){
        return r;
    }

    public static <S1, S2, SR> SR staticMethod(S1 a, S2 b, SR r){
        return r;
    }

}
