package com.tntrip.interview;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by libing2 on 2016/10/9.
 */
public class Choice {
    public void print(Collection<?> coll) {
        System.out.println("I'm Collection");
    }

    public void print(List<?> coll) {
        System.out.println("I'm List");
    }

    public void print(Set<?> coll) {
        System.out.println("I'm Set");
    }

    public static void main(String[] args) {
        Choice pe = new Choice();

        Collection<?> c1 = Arrays.asList("One", "Two", "Three");
        pe.print(c1);

        Set<?> c2 = new HashSet<>(Arrays.asList("One", "Two", "Three"));
        pe.print(c2);
    }
}
