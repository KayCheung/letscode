package com.tntrip.understand.generic;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 1. Class 上的方法
 * <p>
 * 2. getFields(), getDeclaredFields()
 * <p>
 * Created by nuc on 2016/6/11.
 */
public class GetXXXOrGetDeclaredXXX_onClass {
    private static class SuperType {
        public String superPublicField;
        private String superPrivateField;

        public String public_method_str() {
            return null;
        }

        private String private_method_str() {
            return null;
        }
    }

    private static class SubType extends SuperType {
        public Date subPublicField;
        private Date subPrivateField;

        public Date date_public_method() {
            return null;
        }

        private Date date_private_method() {
            return null;
        }
    }

    private static void getXXX_getDeclaredXXX() {
        // 所有 public field。包括继承来的
        for (Field f : SubType.class.getFields()) {
            System.out.println("getFields() : " + f);
        }
        System.out.println();

        // 所有 public/protected/private/default field
        // 但，不包括 继承来的
        for (Field f : SubType.class.getDeclaredFields()) {
            System.out.println("getDeclaredFields() : " + f);
        }
    }

    public static void main(String[] args) {
        getXXX_getDeclaredXXX();
    }
}
