package com.tntrip.understand.generic;

import java.lang.reflect.Field;

/**
 * Created by nuc on 2016/6/11.
 */
public class DistinguishGenericOrParameterized {
    public static void seeStaticDynamicType() throws Exception {

        Field field = Declared_Dynamic_Actual.class.getDeclaredField("first");
        AnalyzeType.analyzeType(field.getGenericType());

        AnalyzeType.analyzeType(field.getType());
    }

    public static void main(String[] args) throws Exception{
        seeStaticDynamicType();
    }
}
