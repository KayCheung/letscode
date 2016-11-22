package com.tntrip.understand.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Supplier;

/**
 * Created by nuc on 2016/11/22.
 */
public class LambdaException {
    public static void main(String[] args) {
        execSupplier(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    return readFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return "error";
                }
            }
        });

        execSupplier(() -> {
            try {
                return readFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "";
            }
        });

        //execSupplier(LambdaException::readFile);


        execSupplier(LambdaException::readFileNoException);
    }

    private static String readFile() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("");
        return fis.toString();
    }

    private static String readFileNoException(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return fis.toString();
    }

    private static void execSupplier(Supplier<String> sp) {
        String str = sp.get();
        System.out.println(str);
    }
}
